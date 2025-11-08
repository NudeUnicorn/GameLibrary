package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHAuthor
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHCheat
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHGame
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHTag
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatsBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithTagsBEH
import com.adorablehappens.gamelibrary.dblogic.dao.DAOAuthor
import com.adorablehappens.gamelibrary.dblogic.dao.DAOCheat
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGame
import com.adorablehappens.gamelibrary.dblogic.dao.DAOTag
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithTagsDAO
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

    private lateinit var daoAuthorRepo: DAOAuthor
    val behAuthorRepo: BEHAuthor = BEHAuthor
    private lateinit var daoCheatRepo: DAOCheat
    val behCheatRepo: DBDaoBehaviour<CheatEntity> = BEHCheat
    private lateinit var daoGameRepo: DAOGame
    val behGameRepo: BEHGame = BEHGame
    private lateinit var daoTagRepo: DAOTag
    val behTagRepo: BEHTag = BEHTag
    private lateinit var joinGameWithTagsDAORepo: JOINGameWithTagsDAO
    val joinGameWithTagsBEHRepo: JOINGameWithTagsBEH = JOINGameWithTagsBEH
    private lateinit var joinGameWithCheatDAORepo: JOINGameWithCheatsDAO
    val joinGameWithCheatBEHRepo: JOINGameWithCheatsBEH = JOINGameWithCheatsBEH
//    private lateinit var oneGameFullInfoDAORepo: DBDaoOneFullInfo
//    val oneGameFullInfoBehaviourRepo: OneGameFullInfoBehaviour = OneGameFullInfoBehaviour

    lateinit var gameEntities: LiveData<List<GameEntity>>
    lateinit var gameEntityCurrent: LiveData<GameEntity>
    var gameEntityCurrentID: Long = 0
    lateinit var cheatEntitiesCurrent: LiveData<POJOGameWithCheats>
    lateinit var genreEntitiesCurrent: LiveData<POJOGameWithGenres>
    lateinit var tagEntitiesCurrent: LiveData<POJOGameWithTags>
    lateinit var authorEntitiesCurrent: LiveData<POJOCheatWithAuthors>
    lateinit var devEntitiesCurrent: LiveData<POJOGameWithDevs>
//    lateinit var countryEntitiesCurrent: LiveData<List<CountryEntity>>
//    lateinit var languageEntitiesCurrent: LiveData<List<LanguageEntity>>
    lateinit var gameEngineEntitiesCurrent: LiveData<POJOGameWithEngines>
    lateinit var walkthroughEntitiesCurrent: LiveData<POJOGameWithWalkthroughes>
    lateinit var walkthroughImageEntitiesCurrent: LiveData<POJOWalkthroughWithImages>

    lateinit var genreEntitiesAll: LiveData<List<GenreEntity>>
    lateinit var tagEntitiesAll: LiveData<List<TagEntity>>
    lateinit var authorEntitiesAll: LiveData<List<AuthorEntity>>
    lateinit var gameEngineEntitiesAll: LiveData<List<GameEngineEntity>>
//  lateinit var countryEntitiesAll: LiveData<List<CountryEntity>>
//  lateinit var languageEntitiesAll: LiveData<List<LanguageEntity>>


    init {

    }

    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context, scope: CoroutineScope){
        db = DB.getInstance(context)
        coroutineScope = scope

        daoAuthorRepo = db.createAuthorDAO()
        behAuthorRepo.setDAO(daoAuthorRepo)

        daoCheatRepo = db.createCheatDAO()
        behCheatRepo.setDAO(daoCheatRepo)

        daoGameRepo = db.createGameDAO()
        behGameRepo.setDAO(daoGameRepo)

        daoTagRepo = db.createDAOTag()
        behTagRepo.setDAO(daoTagRepo)

        joinGameWithTagsDAORepo = db.createJOINGameWithTagsDAO()
        joinGameWithTagsBEHRepo.obj.setDAO(joinGameWithTagsDAORepo)

        joinGameWithCheatDAORepo = db.createJOINGameWithCheatDAO()
        joinGameWithCheatBEHRepo.obj.setDAO(joinGameWithCheatDAORepo)
//        oneGameFullInfoDAORepo = db.createOneGameFullInfoDAO()
//        oneGameFullInfoBehaviourRepo.setDAO(oneGameFullInfoDAORepo)


    }

    fun getCheatBehaviour(): DBDaoBehaviour<CheatEntity> {
        Repository.Constants.AuthorConstants.AuthorQueries.Quer.q.query

        return behCheatRepo
    }

    /**
     * Принимает функцию и выполняет её в фоновом потоке корутины
     */
    fun execCoroutine( func: suspend ()-> Unit){
        coroutineScope.launch {
            func()
        }
    }

    /**
     * Здесь выполняются необходимые действия при завершении работы приложения
     */
    fun onFinish(coroutineCloseMsg: String = ""){
        coroutineScope.cancel(coroutineCloseMsg)
    }

    class Data(){
        fun getAllGames(id: Long? = null){
            try {
                when(id){
                    null -> gameEntities = behGameRepo.getAll()
                    else -> gameEntityCurrent = behGameRepo.getOne(id)
                }
            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllGenres(id: Long? = null){
            try {
                when(id){
                    null -> genreEntitiesAll
                    else -> genreEntitiesCurrent
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllCheats(id: Long){
            try {
                cheatEntitiesCurrent = joinGameWithCheatBEHRepo.obj.getOneLinkedEntity(id)
            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllAuthors(id: Long? = null){
            try {
                authorEntitiesAll = BEHAuthor.getAll()
            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllCountries(id: Long? = null){
            try {
                when(id){
                    null -> genreEntitiesAll
                    else -> genreEntitiesCurrent
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllDevs(id: Long? = null){
            try {
                when(id){
                    null -> devEntitiesCurrent
                    else -> devEntitiesCurrent
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllLanguages(id: Long? = null){
            try {
                when(id){
                    null -> devEntitiesCurrent
                    else -> devEntitiesCurrent
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllTags(id: Long? = null){
            try {
                when(id){
                    null -> tagEntitiesAll = behTagRepo.getAll()
                    else -> tagEntitiesCurrent = joinGameWithTagsBEHRepo.obj.getOneLinkedEntity(id)
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
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