package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.adorablehappens.gamelibrary.dblogic.pojo.OneGameFullInfo

/**
 * Общий дао-интерфейс для получения связанных сущностей
 *
 */
@Dao
interface DBDaoOneFullInfo {
//    /**
//     * Создаёт новую сущность
//     */
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun entityInsert(entity: EntityBase)
//
//    /**
//     * Обновляет запись сущности в базе данных
//     */
//    @Update(onConflict = OnConflictStrategy.IGNORE)
//    fun entityUpdate(entity: EntityBase)
//
//    /**
//     * Удаляет сущность
//     */
//    @Delete
//    fun entityDelete(entity: EntityBase)
//
//    /**
//     * Создаёт связь между сущностями в промежуточной сущности-таблице
//     */
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun joinInsert(join: JOINEntity)
//
//    /**
//     * Удаляет связь между сущностями
//     */
//    @Delete
//    fun joinDelete(join: JOINEntity)

    /**
     * Возвращает все связанные сущности из таблицы
     */
    @Transaction
    @Query("SELECT * FROM games")
    fun getAllLinkedEntities() : LiveData<List<OneGameFullInfo>>

    /**
     * Возвращает одну сущность из таблицы
     */
    @Transaction
    @Query("SELECT * FROM games WHERE id=:id")
    fun getOneLinkedEntity(id: Long) : LiveData<OneGameFullInfo>

}