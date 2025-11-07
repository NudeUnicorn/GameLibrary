package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.behaviour.AuthorBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.CheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.GameBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.OneGameFullInfoBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.AuthorDAO
import com.adorablehappens.gamelibrary.dblogic.dao.CheatDAO
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoOneFullInfo
import com.adorablehappens.gamelibrary.dblogic.dao.GameDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CountryEntity
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.LanguageEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughEntity
import com.adorablehappens.gamelibrary.dblogic.entities.WalkthroughImageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Хранит и управляет всеми данными на уровне приложения
 */
object Repository {

    private lateinit var db: DB
    private lateinit var coroutineScope: CoroutineScope

    private lateinit var authorDAORepo: AuthorDAO
    val authorBehaviourRepo: AuthorBehaviour = AuthorBehaviour
    private lateinit var cheatDAORepo: CheatDAO
    val cheatBehaviourRepo: DBDaoBehaviour<CheatEntity> = CheatBehaviour
    private lateinit var gameDAORepo: GameDAO
    val gameBehaviourRepo: GameBehaviour = GameBehaviour
    private lateinit var joinGameWithCheatDAORepo: JOINGameWithCheatDAO
    val joinGameWithCheatBehaviourRepo: JOINGameWithCheatBehaviour = JOINGameWithCheatBehaviour
//    private lateinit var oneGameFullInfoDAORepo: DBDaoOneFullInfo
//    val oneGameFullInfoBehaviourRepo: OneGameFullInfoBehaviour = OneGameFullInfoBehaviour

    lateinit var gameEntities: LiveData<List<GameEntity>>
    lateinit var gameEntityCurrent: LiveData<GameEntity>
    lateinit var cheatEntities: LiveData<List<CheatEntity>>
    lateinit var genreEntities: LiveData<List<GenreEntity>>
    lateinit var tagEntities: LiveData<List<TagEntity>>
    lateinit var authorEntities: LiveData<List<AuthorEntity>>
    lateinit var devEntities: LiveData<List<DevEntity>>
    lateinit var countryEntities: LiveData<List<CountryEntity>>
    lateinit var languageEntities: LiveData<List<LanguageEntity>>
    lateinit var gameEngineEntities: LiveData<List<GameEngineEntity>>
    lateinit var walkthroughEntities: LiveData<List<WalkthroughEntity>>
    lateinit var walkthroughImageEntities: LiveData<List<WalkthroughImageEntity>>


    init {

    }

    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context, scope: CoroutineScope){
        db = DB.getInstance(context)
        coroutineScope = scope

        authorDAORepo = db.getAuthorDAO()
        authorBehaviourRepo.setDAO(authorDAORepo)
        cheatDAORepo = db.getCheatDAO()
        cheatBehaviourRepo.setDAO(cheatDAORepo)
        gameDAORepo = db.getGameDAO()
        gameBehaviourRepo.setDAO(gameDAORepo)
        joinGameWithCheatDAORepo = db.getJOINGameWithCheatDAO()
        joinGameWithCheatBehaviourRepo.setDAO(joinGameWithCheatDAORepo)
//        oneGameFullInfoDAORepo = db.createOneGameFullInfoDAO()
//        oneGameFullInfoBehaviourRepo.setDAO(oneGameFullInfoDAORepo)


    }

    fun getCheatBehaviour(): DBDaoBehaviour<CheatEntity> {
        Repository.Constants.AuthorConstants.AuthorQueries.Quer.q.query

        return cheatBehaviourRepo
    }

    fun execCoroutine( func: suspend ()-> Unit){
        coroutineScope.launch {
            func()
        }
    }

    fun onFinish(coroutineMsg: String = ""){
        coroutineScope.cancel(coroutineMsg)
    }

    class Constants private constructor(){

        class AuthorConstants{

            enum class AuthorQueries(
                val q: Query
            ){
                Quer(Query("s"))
            }

        }



    }

    data class Query(
        val query: String
    )


}