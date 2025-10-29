package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData

interface DBEntityBehaviour <T, Y>: DBDaoBehaviour<T>{
    var entityList : LiveData<List<T>>
    var entityCurrent : T
    var entityDAO : Y //здесь тип DAO-класса(например, ProjDAO), сгенерированный объектом базы данных

    /**
     * Передаётся необходимая информация для инициализации и работы в целом. Например, ссылка на объект БД или репозитория
     */
    fun setDAO(dao : Y){  //в общем, идея в том, что вызывающая сторона возвращает что-то что тут же присваивается свойству
        entityDAO = dao  //здесь должен вернуться DAO-класс, сгенерированный объектом базы данных
    }
}