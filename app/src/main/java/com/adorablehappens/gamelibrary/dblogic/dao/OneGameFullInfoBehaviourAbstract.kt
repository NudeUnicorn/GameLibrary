package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.Repository
import com.adorablehappens.gamelibrary.dblogic.pojo.OneGameFullInfo

abstract class OneGameFullInfoBehaviourAbstract: DBDaoOneFullInfo {
    protected lateinit var entityDAO : DBDaoOneFullInfo

    /**
     * Передаётся необходимая информация для инициализации и работы в целом
     */
    fun setDAO(dao : DBDaoOneFullInfo){
        entityDAO = dao
    }

//    /**
//     * Создаёт новую сущность
//     */
//    override fun entityInsert(entity: EntityBase) {
//        Repository.execCoroutine {
//            entityDAO.entityInsert(entity)
//        }
//    }
//
//    /**
//     * Обновляет запись сущности в базе данных
//     */
//    override fun entityUpdate(entity: EntityBase) {
//        Repository.execCoroutine {
//            entityDAO.entityUpdate(entity)
//        }
//    }
//
//    /**
//     * Удаляет сущность
//     */
//    override fun entityDelete(entity: EntityBase) {
//        Repository.execCoroutine {
//            entityDAO.entityDelete(entity)
//        }
//    }
//
//    /**
//     * Создаёт связь между сущностями в промежуточной сущности-таблице
//     */
//    override fun joinInsert(join: JOINEntity) {
//        Repository.execCoroutine {
//            entityDAO.joinInsert(join)
//        }
//    }
//
//    /**
//     * Удаляет связь между сущностями
//     */
//    override fun joinDelete(join: JOINEntity) {
//        Repository.execCoroutine {
//            entityDAO.joinDelete(join)
//        }
//    }

    /**
     * Возвращает все связанные сущности из таблицы
     */
    override fun getAllLinkedEntities(): LiveData<List<OneGameFullInfo>> {
        return entityDAO.getAllLinkedEntities()
    }

    /**
     * Возвращает одну сущность из таблицы
     */
    override fun getOneLinkedEntity(id: Long): LiveData<OneGameFullInfo> {
        return entityDAO.getOneLinkedEntity(id)
    }


}