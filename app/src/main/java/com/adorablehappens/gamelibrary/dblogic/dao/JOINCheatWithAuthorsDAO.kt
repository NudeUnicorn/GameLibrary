package com.adorablehappens.gamelibrary.dblogic.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.UCheatAuthorEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOCheatWithAuthors

@Dao
interface JOINCheatWithAuthorsDAO: DBDaoJoin<CheatEntity, AuthorEntity, UCheatAuthorEntity, POJOCheatWithAuthors> {

    @Transaction
    @Query("SELECT * FROM cheats")
    override fun getAllLinkedEntities(): LiveData<List<POJOCheatWithAuthors>>

    @Transaction
    @Query("SELECT * FROM cheats WHERE id=:id")
    override fun getOneLinkedEntity(id: Long): LiveData<POJOCheatWithAuthors>


}