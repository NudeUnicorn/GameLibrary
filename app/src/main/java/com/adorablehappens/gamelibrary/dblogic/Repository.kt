package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHAuthor
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHCheat
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHCountry
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHDev
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHGame
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHGameEngine
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHGenre
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHLanguage
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHTag
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHWalkthrough
import com.adorablehappens.gamelibrary.dblogic.behaviour.BEHWalkthroughImage
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINCheatWithAuthorsBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithCheatsBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithDevsBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithEnginesBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithGenresBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithTagsBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINGameWithWalkthroughesBEH
import com.adorablehappens.gamelibrary.dblogic.behaviour.JOINWalkthroughWithImagesBEH
import com.adorablehappens.gamelibrary.dblogic.dao.DAOAuthor
import com.adorablehappens.gamelibrary.dblogic.dao.DAOCheat
import com.adorablehappens.gamelibrary.dblogic.dao.DAOCountry
import com.adorablehappens.gamelibrary.dblogic.dao.DAODev
import com.adorablehappens.gamelibrary.dblogic.dao.DBDaoBehaviour
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGame
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGameEngine
import com.adorablehappens.gamelibrary.dblogic.dao.DAOGenre
import com.adorablehappens.gamelibrary.dblogic.dao.DAOLanguage
import com.adorablehappens.gamelibrary.dblogic.dao.DAOTag
import com.adorablehappens.gamelibrary.dblogic.dao.DAOWalkthrough
import com.adorablehappens.gamelibrary.dblogic.dao.DAOWalkthroughImage
import com.adorablehappens.gamelibrary.dblogic.dao.JOINCheatWithAuthorsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithCheatsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithDevsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithEnginesDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithGenresDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithTagsDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINGameWithWalkthroughDAO
import com.adorablehappens.gamelibrary.dblogic.dao.JOINWalkthroughWithImagesDAO
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
import kotlin.lazy

/**
 * Хранит и управляет всеми данными на уровне приложения
 */
object Repository {

    private lateinit var db: DB
    private lateinit var coroutineScope: CoroutineScope

//    private lateinit var daoAuthorRepo: DAOAuthor
//    val behAuthorRepo: BEHAuthor = BEHAuthor
    private val daoAuthorRepo: DAOAuthor by lazy { db.createAuthorDAO() }
    val behAuthorRepo: BEHAuthor by lazy { BEHAuthor.apply { setDAO(daoAuthorRepo) } }

//    private lateinit var daoCheatRepo: DAOCheat
//    val behCheatRepo: DBDaoBehaviour<CheatEntity> = BEHCheat
    private val daoCheatRepo: DAOCheat by lazy { db.createCheatDAO() }
    val behCheatRepo: BEHCheat by lazy { BEHCheat.apply { setDAO(daoCheatRepo) } }

    private val daoCountryRepo: DAOCountry by lazy { db.createDAOCountry() }
    val behCountryRepo: BEHCountry by lazy { BEHCountry.apply { setDAO(daoCountryRepo) } }

    private val daoDevRepo: DAODev by lazy { db.createDAODev() }
    val behDevRepo: BEHDev by lazy { BEHDev.apply { setDAO(daoDevRepo) } }

//    private lateinit var daoGameRepo: DAOGame
//    val behGameRepo: BEHGame = BEHGame

    private val daoGameRepo: DAOGame by lazy { db.createDAOGame() }
    val behGameRepo: BEHGame by lazy { BEHGame.apply { setDAO(daoGameRepo) } }

    private val daoGameEngineRepo: DAOGameEngine by lazy { db.createDAOGameEngine() }
    val behGameEngineRepo: BEHGameEngine by lazy { BEHGameEngine.apply { setDAO(daoGameEngineRepo) } }

    private val daoGenreRepo: DAOGenre by lazy { db.createDAOGenre() }
    val behGenreRepo: BEHGenre by lazy { BEHGenre.apply { setDAO(daoGenreRepo) } }

    private val daoLanguageRepo: DAOLanguage by lazy { db.createDAOLanguage() }
    val behLanguageRepo: BEHLanguage by lazy { BEHLanguage.apply { setDAO(daoLanguageRepo) } }

//    private lateinit var daoTagRepo: DAOTag
//    val behTagRepo: BEHTag = BEHTag
    private val daoTagRepo: DAOTag by lazy { db.createDAOTag() }
    val behTagRepo: BEHTag by lazy { BEHTag.apply { setDAO(daoTagRepo) } }


    private val daoWalkthroughRepo: DAOWalkthrough by lazy { db.createDAOWalkthrough() }
    val behWalkthroughRepo: BEHWalkthrough by lazy { BEHWalkthrough.apply { setDAO(daoWalkthroughRepo) } }

    private val daoWalkthroughImageRepo: DAOWalkthroughImage by lazy { db.createDAOWalkthroughImage() }
    val behWalkthroughImageRepo: BEHWalkthroughImage by lazy { BEHWalkthroughImage.apply { setDAO(daoWalkthroughImageRepo) } }


    private val daoJOINCheatWithAuthorsRepo: JOINCheatWithAuthorsDAO by lazy { db.createJOINCheatWithAuthorsDAO() }
    val behJOINCheatWithAuthorsRepo: JOINCheatWithAuthorsBEH by lazy { JOINCheatWithAuthorsBEH.apply { this.obj.setDAO(daoJOINCheatWithAuthorsRepo) } }

//    private lateinit var daoJOINGameWithCheatRepo: JOINGameWithCheatsDAO
//    val behJOINGameWithCheatRepo: JOINGameWithCheatsBEH = JOINGameWithCheatsBEH
    private val daoJOINGameWithCheatRepo: JOINGameWithCheatsDAO by lazy { db.createJOINGameWithCheatsDAO() }
    val behJOINGameWithCheatRepo: JOINGameWithCheatsBEH by lazy { JOINGameWithCheatsBEH.apply { this.obj.setDAO(daoJOINGameWithCheatRepo) } }

    private val daoJOINGameWithDevsRepo: JOINGameWithDevsDAO by lazy { db.createJOINGameWithDevsDAO() }
    val behJOINGameWithDevsRepo: JOINGameWithDevsBEH by lazy { JOINGameWithDevsBEH.apply { this.obj.setDAO(daoJOINGameWithDevsRepo) } }

    private val daoJOINGameWithEnginesRepo: JOINGameWithEnginesDAO by lazy { db.createJOINGameWithEnginesDAO() }
    val behJOINGameWithEnginesRepo: JOINGameWithEnginesBEH by lazy { JOINGameWithEnginesBEH.apply { this.obj.setDAO(daoJOINGameWithEnginesRepo) } }

    private val daoJOINGameWithGenresRepo: JOINGameWithGenresDAO by lazy { db.createJOINGameWithGenresDAO() }
    val behJOINGameWithGenresRepo: JOINGameWithGenresBEH by lazy { JOINGameWithGenresBEH.apply { this.obj.setDAO(daoJOINGameWithGenresRepo) } }

//    private lateinit var daoJOINGameWithTagsRepo: JOINGameWithTagsDAO
//    val behJOINGameWithTagsRepo: JOINGameWithTagsBEH = JOINGameWithTagsBEH
    private val daoJOINGameWithTagsRepo: JOINGameWithTagsDAO by lazy { db.createJOINGameWithTagsDAO() }
    val behJOINGameWithTagsRepo: JOINGameWithTagsBEH by lazy { JOINGameWithTagsBEH.apply { this.obj.setDAO(daoJOINGameWithTagsRepo) } }

    private val daoJOINGameWithWalkthroughRepo: JOINGameWithWalkthroughDAO by lazy { db.createJOINGameWithWalkthroughDAO() }
    val behJOINGameWithWalkthroughesRepo: JOINGameWithWalkthroughesBEH by lazy { JOINGameWithWalkthroughesBEH.apply { this.obj.setDAO(daoJOINGameWithWalkthroughRepo) } }

    private val daoJOINWalkthroughWithImagesRepo: JOINWalkthroughWithImagesDAO by lazy { db.createJOINWalkthroughWithImagesDAO() }
    val behJOINWalkthroughWithImagesRepo: JOINWalkthroughWithImagesBEH by lazy { JOINWalkthroughWithImagesBEH.apply { this.obj.setDAO(daoJOINWalkthroughWithImagesRepo) } }


//    private lateinit var oneGameFullInfoDAORepo: DBDaoOneFullInfo
//    val oneGameFullInfoBehaviourRepo: OneGameFullInfoBehaviour = OneGameFullInfoBehaviour

    var gameEntities: LiveData<List<GameEntity>>
        get() = behGameRepo.getAll()
        set(value) {value}
    val gameEntitiesFunc: ()->LiveData<List<GameEntity>> = {
        behGameRepo.getAll()
    }
    val gameEntitiesLazy: LiveData<List<GameEntity>> by lazy { behGameRepo.getAll() }
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

//        daoAuthorRepo = db.createAuthorDAO()
//        behAuthorRepo.setDAO(daoAuthorRepo)
//
//        daoCheatRepo = db.createCheatDAO()
//        behCheatRepo.setDAO(daoCheatRepo)

//        daoGameRepo = db.createDAOGame()
//        behGameRepo.setDAO(daoGameRepo)

//        daoTagRepo = db.createDAOTag()
//        behTagRepo.setDAO(daoTagRepo)

//        daoJOINGameWithTagsRepo = db.createJOINGameWithTagsDAO()
//        behJOINGameWithTagsRepo.obj.setDAO(daoJOINGameWithTagsRepo)
//
//        daoJOINGameWithCheatRepo = db.createJOINGameWithCheatsDAO()
//        behJOINGameWithCheatRepo.obj.setDAO(daoJOINGameWithCheatRepo)
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
                cheatEntitiesCurrent = behJOINGameWithCheatRepo.obj.getOneLinkedEntity(id)
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
                    else -> tagEntitiesCurrent = behJOINGameWithTagsRepo.obj.getOneLinkedEntity(id)
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