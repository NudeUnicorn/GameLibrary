package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.behaviour.AuthorBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.CheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.GameBehaviour
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.DAOAuthor
import com.adorablehappens.gamelibrary.dblogic.dao.DAOCheat
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGame
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatDAO
import com.adorablehappens.gamelibrary.dblogic.entities.AuthorEntity
import com.adorablehappens.gamelibrary.dblogic.entities.CheatEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEngineEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GameEntity
import com.adorablehappens.gamelibrary.dblogic.entities.GenreEntity
import com.adorablehappens.gamelibrary.dblogic.entities.TagEntity
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOCheatWithAuthors
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithCheats
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithDevs
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithEngines
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithGenres
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithTags
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOGameWithWalkthroughes
import com.adorablehappens.gamelibrary.dblogic.pojo.POJOWalkthroughWithImages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Хранит и управляет всеми данными на уровне приложения
 */
object Repository {

    private lateinit var db: DB
    private lateinit var coroutineScope: CoroutineScope

    private lateinit var authorDAORepo: DAOAuthor
    val authorBehaviourRepo: AuthorBehaviour = AuthorBehaviour
    private lateinit var cheatDAORepo: DAOCheat
    val cheatBehaviourRepo: DBDaoBehaviour<CheatEntity> = CheatBehaviour
    private lateinit var gameDAORepo: DAOGame
    val gameBehaviourRepo: GameBehaviour = GameBehaviour
    private lateinit var joinGameWithCheatDAORepo: JOINGameWithCheatDAO
    val joinGameWithCheatBehaviourRepo: JOINGameWithCheatBehaviour = JOINGameWithCheatBehaviour
//    private lateinit var oneGameFullInfoDAORepo: DBDaoOneFullInfo
//    val oneGameFullInfoBehaviourRepo: OneGameFullInfoBehaviour = OneGameFullInfoBehaviour

    lateinit var gameEntities: LiveData<List<GameEntity>>
    lateinit var gameEntityCurrent: LiveData<GameEntity>
    var gameEntityCurrentID: Long = 0
    lateinit var cheatEntities: LiveData<List<POJOGameWithCheats>>
    lateinit var genreEntities: LiveData<List<POJOGameWithGenres>>
    lateinit var tagEntities: LiveData<List<POJOGameWithTags>>
    lateinit var authorEntities: LiveData<List<POJOCheatWithAuthors>>
    lateinit var devEntities: LiveData<List<POJOGameWithDevs>>
//    lateinit var countryEntities: LiveData<List<CountryEntity>>
//    lateinit var languageEntities: LiveData<List<LanguageEntity>>
    lateinit var gameEngineEntities: LiveData<List<POJOGameWithEngines>>
    lateinit var walkthroughEntities: LiveData<List<POJOGameWithWalkthroughes>>
    lateinit var walkthroughImageEntities: LiveData<List<POJOWalkthroughWithImages>>

    lateinit var genreEntitiesAll: LiveData<List<GenreEntity>>
    lateinit var tagEntitiesAll: LiveData<List<TagEntity>>
    lateinit var authorEntitiesAll: LiveData<List<AuthorEntity>>
    lateinit var gameEngineEntitiesAll: LiveData<List<GameEngineEntity>>


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