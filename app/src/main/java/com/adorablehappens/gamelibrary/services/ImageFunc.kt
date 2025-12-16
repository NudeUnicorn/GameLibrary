package com.adorablehappens.gamelibrary.services

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.graphics.createBitmap
import java.io.File

abstract class ImageFunc {

    /**
     * Лучше передавать контекст приложения, но сойдёт и локальный
     */
    private lateinit var context: Context

    /**
     * Самая первая функция, которая должна быть вызвана.
     * Задаёт контекст. Без него всё остальное не работает
     */
    fun setContext(contextLocal: Context){context = contextLocal}

//    /**
//     * Создаёт и запускает интент с выбором изображения и возвращает его uri
//     */
//    @Composable
//    fun selectImage(): Uri?{
//        var uri: Uri? = null
//
//        val launcher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.GetContent(),
//            onResult = { uriResult ->
//                if (uriResult !== null)
//                    uri = uriResult
//            }
//        )
//        launcher.launch("image/*")
//
//        return uri
//    }

    /**
     * Сохраняет изображение во внутренне хранилище и возвращает его имя
     */
    fun saveImageToInternalStorage(uri: Uri?): String?{
        return try {
            uri?.let {

                // Генерируем уникальное имя файла
                val filename = getNewImageFilename()
                var bitmap = createBitmap(1,1)

                // Открываем поток для записи
                context.openFileOutput(filename, Context.MODE_PRIVATE).use {
                        outputStream ->

                    // Читаем из URI
                    context.contentResolver.openInputStream(uri).use { inputStream ->
                        //inputStream?.copyTo(outputStream)
                        bitmap = BitmapFactory.decodeStream(inputStream)
                    }

                    bitmap.compress(Bitmap.CompressFormat.WEBP, 80, outputStream)
                }

                // Возвращаем имя файла
                filename
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    /**
     * Возвращает изображение, если есть или null
     */
    fun getImageFromInternalStorage(filename: String): Bitmap?{
        return try {

            val file = File(context.filesDir,filename)

            BitmapFactory.decodeFile(file.absolutePath)
        }
        catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    /**
     * Показывает изображение из uri
     */
    fun previewImage(uri: Uri?): Bitmap?{
        return try {
            var image: Bitmap? = null
            uri?.let {
                // Читаем из URI
                context.contentResolver.openInputStream(uri).use { inputStream ->
                    image = BitmapFactory.decodeStream(inputStream)
                }
            }

            image

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Удаляет изображение
     */
    fun deleteImage(filename: String): Boolean{
        return try {
            if(filename.isNotBlank()){
                val imageFile = File(context.filesDir,filename)

                return imageFile.delete()
            }
            return false

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Определяет и возвращает новое уникальное имя изображения согласно паттерну
     */
    fun getNewImageFilename(): String{
        return "IMG_${System.currentTimeMillis()}.webp"
    }
}