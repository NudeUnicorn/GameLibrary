package com.adorablehappens.gamelibrary.services

import android.graphics.Bitmap
import com.adorablehappens.gamelibrary.dblogic.Repository

/**
 * Интерфейс для создания и управления системой кэша изображений
 */
interface ImageCacher {

    /**
     * Изменяемая карта кэша изображений
     */
    val imagesCached: MutableMap<Long, Bitmap>

    /**
     * Максимальное количество изображений в кэше
     */
    var imagesCachedMapMaxSize: Int

    /**
     * Добавить изображение в кэш, если кэш переполнен удаляет первое в коллекции
     */
    private fun imageAdd(id: Long, image: Bitmap){
        imagesCachedMapMaxSize = if (imagesCachedMapMaxSize >= 5) imagesCachedMapMaxSize else 5

        if (imagesCached.size >= imagesCachedMapMaxSize){
            imagesCached.remove(imagesCached.keys.first())
        }
        imagesCached.put(id, image)
    }

    /**
     * Удаляет изображение из кэша
     */
    private fun imageRemove(id: Long): Bitmap?{
        return imagesCached.remove(id)
    }

    /**
     * Получает изображение из кэша если оно там есть, если нет,
     * пытается загрузить, добавить в кэш и вернуть
     */
    fun imageGet(id: Long, filename: String?): Bitmap? {
        if (imagesCached.contains(id)){

            return imagesCached[id]
        }
        else{
            val image = filename?.let {imageName->
                Repository.imageManager.getImageFromInternalStorage(imageName)
            }
            image?.let {
                imageAdd(id, it)
            }

            return image
        }
    }
}