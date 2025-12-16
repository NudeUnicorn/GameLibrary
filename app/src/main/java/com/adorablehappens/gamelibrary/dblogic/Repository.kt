package com.adorablehappens.gamelibrary.dblogic

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.adorablehappens.gamelibrary.App
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
import com.adorablehappens.gamelibrary.dblogic.entities.DevEntity
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
import com.adorablehappens.gamelibrary.services.GamesTimeManager
import com.adorablehappens.gamelibrary.services.ImageCacher
import com.adorablehappens.gamelibrary.services.ImageFunc
import com.adorablehappens.gamelibrary.services.OptionsVault
import com.adorablehappens.gamelibrary.services.OptionsVaultDataStore
import com.adorablehappens.gamelibrary.services.OptionsVaultDataStore.Companion.PREFS_FILENAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

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
    //private val daoOneFullInfo1Repo: DBDaoOneFullInfo1 by lazy { db.createOneGameFullInfoDAO() }


    //Данные для одной выбранной сущности игры
    private lateinit var currentGameEntity: LiveData<GameEntity>
    var currentGameEntityID: Long = 1
    private lateinit var currentCheatEntities: LiveData<POJOGameWithCheats>
    private lateinit var currentGenreEntities: LiveData<POJOGameWithGenres>
    private lateinit var currentTagEntities: LiveData<POJOGameWithTags>
    private lateinit var currentAuthorEntities: LiveData<POJOCheatWithAuthors>
    private lateinit var currentDevEntities: LiveData<POJOGameWithDevs>
//    lateinit var currentCountryEntities: LiveData<List<CountryEntity>>
//    lateinit var currentLanguageEntities: LiveData<List<LanguageEntity>>
    private lateinit var currentGameEngineEntities: LiveData<POJOGameWithEngines>
    private lateinit var currentWalkthroughEntities: LiveData<POJOGameWithWalkthroughes>
    private lateinit var currentWalkthroughImageEntities: LiveData<POJOWalkthroughWithImages>

    private lateinit var currentGameState: MutableState<GameEntity>
    private lateinit var currentCheatsState: MutableState<POJOGameWithCheats>
    private lateinit var currentTagsState: MutableState<POJOGameWithTags>
    private lateinit var currentDevsState: MutableState<POJOGameWithDevs>
    private lateinit var currentGenresState: MutableState<POJOGameWithGenres>
    private lateinit var currentGameEngineState: MutableState<POJOGameWithEngines>
    private lateinit var currentWalkthroughesState: MutableState<POJOGameWithWalkthroughes>

    private lateinit var currentTagsComparedState: MutableState<Map<TagEntity, Boolean>>
    private lateinit var currentDevsComparedState: MutableState<Map<DevEntity, Boolean>>
    private lateinit var currentGenresComparedState: MutableState<Map<GenreEntity, Boolean>>
    private lateinit var currentGameEngineComparedState: MutableState<Map<GameEngineEntity, Boolean>>


    //Все сущности опреденного типа
    private val allGameEntities: LiveData<List<GameEntity>> by lazy { behGameRepo.getAll() }
    private val allGenreEntities: LiveData<List<GenreEntity>> by lazy { behGenreRepo.getAll() }
    private val allTagEntities: LiveData<List<TagEntity>> by lazy { behTagRepo.getAll() }
    private val allDevEntities: LiveData<List<DevEntity>> by lazy { behDevRepo.getAll() }
    private val allAuthorEntities: LiveData<List<AuthorEntity>> by lazy { behAuthorRepo.getAll() }
    private val allGameEngineEntities: LiveData<List<GameEngineEntity>> by lazy { behGameEngineRepo.getAll() }
//  private lateinit var allCountryEntities: LiveData<List<CountryEntity>>
//  private lateinit var allLanguageEntities: LiveData<List<LanguageEntity>>


    lateinit var allGamesState: MutableState<List<GameEntity>>
    lateinit var allGenresState: MutableState<List<GenreEntity>>
    lateinit var allTagsState: MutableState<List<TagEntity>>
    lateinit var allDevsState: MutableState<List<DevEntity>>
    lateinit var allAuthorsState: MutableState<List<AuthorEntity>>
    lateinit var allGameEnginesState: MutableState<List<GameEngineEntity>>

    val imageManager = object : ImageFunc(){}
    val imageCacher = object : ImageCacher{
        override val imagesCached: MutableMap<Long, Bitmap> = mutableMapOf()
        override var imagesCachedMapMaxSize: Int = 20
    }
    val gamesTimeManager = GamesTimeManager

    lateinit var appOptions: OptionsVault
    private val Context.dataStore by preferencesDataStore(PREFS_FILENAME)

    lateinit var appOptionsDataStore: OptionsVaultDataStore
    init {

    }

    private lateinit var app: App
    /**
     * Функция для передачи контекста в объект репозитория для создания синглтона базы данных
     */
    fun initDB(context: Context, scope: CoroutineScope, appF: App){
        app = appF
        db = DB.getInstance(context)
        coroutineScope = scope
        imageManager.setContext(context)
        appOptions = object : OptionsVault(
            {prefsName ->
                context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            }
        ){}
        appOptionsDataStore = object : OptionsVaultDataStore(
            {prefsName->
                context.dataStore
            }){}
//        appOptionsDataStore = object : OptionsVaultDataStore(
//            {prefsName ->
//                PreferenceDataStoreFactory.create {
//                    context.dataStoreFile(prefsName)
//                }
//            }
//        ){}

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

    /**
     * Позволяет получить строку из ресурсов там, где нет возможности получить context
     */
    fun getStringResRepo(stringResID: Int): String{
        return app.applicationContext.getString(stringResID)
    }
    /**
     * Позволяет получить строку из ресурсов там, где нет возможности получить context. С форматированием.
     */
    fun getStringResRepo(stringResID: Int,vararg formatArgs: Any): String{
        return app.applicationContext.getString(stringResID, formatArgs)
    }
    /**
     * Возвращает контекст приложения
     */
    fun getAppContext(): Context? {
        return app.applicationContext
    }

    /**
     * Принимает функцию и выполняет её в фоновом потоке корутины
     */
    fun execCoroutine( coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main, func: suspend ()-> Unit){
        coroutineScope.launch(coroutineDispatcher) {
            func()
        }
    }

    /**
     * Здесь выполняются необходимые действия при завершении работы приложения
     */
    fun onFinish(coroutineCloseMsg: String = ""){
        coroutineScope.cancel(coroutineCloseMsg)
    }

    object Data{
        fun getAllGames(id: Long? = null){
            try {
                when(id){
                    null -> {}//allGameEntities = behGameRepo.getAll()
                    else -> currentGameEntity = behGameRepo.getOne(id)
                }
            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllGenres(id: Long? = null){
            try {
                when(id){
                    null -> {}//allGenreEntities = behGenreRepo.getAll()
                    else -> currentGenreEntities = behJOINGameWithGenresRepo.obj.getOneLinkedEntity(id)
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllCurrentCheats(id: Long){
            try {
                currentCheatEntities = behJOINGameWithCheatRepo.obj.getOneLinkedEntity(id)
            }
            catch (e: Exception){
                print(e)
            }
        }

        /**
         * Возвращает все прохождения для конкретной игры
         */
        fun getAllCurrentWalkthroughes(id: Long){
            try {
                currentWalkthroughEntities = behJOINGameWithWalkthroughesRepo.obj.getOneLinkedEntity(id)
            }
            catch (e: Exception){
                print(e)
            }
        }

        /**
         * Возвращает все изображения для конкретного прохождения
         */
        fun getAllCurrentWalkthroughWithImages(id: Long){
            try {
                currentWalkthroughImageEntities = behJOINWalkthroughWithImagesRepo.obj.getOneLinkedEntity(id)
            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllAuthors(id: Long? = null){
            try {

                when(id){
                    null -> {}//allAuthorEntities = behAuthorRepo.getAll()
                    else -> currentAuthorEntities = behJOINCheatWithAuthorsRepo.obj.getOneLinkedEntity(id)
                }
            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllCountries(id: Long? = null){
            try {
                when(id){
                    null -> allGenreEntities
                    else -> currentGenreEntities
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllDevs(id: Long? = null){
            try {
                when(id){
                    null -> {}//allDevEntities = behDevRepo.getAll()
                    else -> currentDevEntities = behJOINGameWithDevsRepo.obj.getOneLinkedEntity(id)
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllLanguages(id: Long? = null){
            try {
                when(id){
                    null -> currentDevEntities
                    else -> currentDevEntities
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllGameEngines(id: Long? = null){
            try {
                when(id){
                    null -> {}//allGameEngineEntities = behGameEngineRepo.getAll()
                    else -> currentGameEngineEntities = behJOINGameWithEnginesRepo.obj.getOneLinkedEntity(id)
                }

            }
            catch (e: Exception){
                print(e)
            }
        }
        fun getAllTags(id: Long? = null){
            try {
                when(id){
                    null -> {}//allTagEntities = behTagRepo.getAll()
                    else -> currentTagEntities = behJOINGameWithTagsRepo.obj.getOneLinkedEntity(id)
                }

            }
            catch (e: Exception){
                print(e)
            }
        }

        /**
         * Получает все данные об одной сущности игры
         */
        fun getAllCurrentGameData(id: Long? = null): AllCurrentLiveData {
            val localID = id ?: currentGameEntityID

            getAllGames(localID)
            getAllCurrentCheats(localID)
            getAllGameEngines(localID)
            getAllGenres(localID)
            getAllDevs(localID)
            getAllTags(localID)
            getAllCurrentWalkthroughes(localID)
            getAllAuthors(localID)

            return AllCurrentLiveData

        }

    }

    enum class ActionType(){
        Show(),
        Create,
        Update()
    }

    /**
     * Объект-обёртка для удобства получения всех ссылок с данными связанных сущностей
     */
    object AllCurrentLiveData{
        val gameEntityCurrentIDObj
            get() = currentGameEntityID
        val currentGameObj: LiveData<GameEntity>
            get() = currentGameEntity
        val currentCheatsObj
            get() = currentCheatEntities
        val currentTagsObj
            get() = currentTagEntities
        val currentDevsObj
            get() = currentDevEntities
        val currentGenresObj
            get() = currentGenreEntities
        val currentGameEngineObj
            get() = currentGameEngineEntities
        val currentWalkthroughesObj
            get() = currentWalkthroughEntities

    }

    /**
     * Объект-обёртка для удобства получения всех ссылок с данными несвязанных сущностей
     */
    object AllGamesData{
        val allGamesStateObj: MutableState<List<GameEntity>> = allGamesState
        val allGenresStateObj: MutableState<List<GenreEntity>> = allGenresState
        val allTagsStateObj: MutableState<List<TagEntity>> = allTagsState
        val allDevsStateObj: MutableState<List<DevEntity>> = allDevsState
        val allAuthorsStateObj: MutableState<List<AuthorEntity>> = allAuthorsState
        val allGameEnginesStateObj: MutableState<List<GameEngineEntity>> = allGameEnginesState
    }

    /**
     * Объект-обёртка для удобства получения всех ссылок с данными несвязанных сущностей
     */
    object AllLiveData{
        val allGameEntitiesObj: LiveData<List<GameEntity>> = allGameEntities
        val allGenreEntitiesObj: LiveData<List<GenreEntity>> = allGenreEntities
        val allTagEntitiesObj: LiveData<List<TagEntity>> = allTagEntities
        val allDevEntitiesObj: LiveData<List<DevEntity>> = allDevEntities
        val allAuthorEntitiesObj: LiveData<List<AuthorEntity>> = allAuthorEntities
        val allGameEngineEntitiesObj: LiveData<List<GameEngineEntity>> = allGameEngineEntities
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

    /**
     * Проверяет вхождение каждого элемента коллекции в общую коллекцию и возвращает карту элементов общей коллекции с булевой отметкой
     *
     * @return null - если какая либо из коллекций null, или общая(all) коллекция пустая
     */
    suspend fun <T>compareAllAndCurrent(all: List<T>?, current:List<T>?): MutableMap<T, Boolean> {
        val comparedMap: MutableMap<T, Boolean> = mutableMapOf<T, Boolean>()

        if (all != null && current != null){
            if (all.isEmpty()){
                return comparedMap
            }
            else{
                all.forEach { it ->
                    if (current.contains(it)){
                        comparedMap.put(it, true)
                    }
                    else{
                        comparedMap.put(it, false)
                    }
                }
            }
        }

        return comparedMap
    }
    /**
     * Проверяет вхождение каждого элемента коллекции в общую коллекцию и возвращает карту элементов общей коллекции с булевой отметкой
     *
     * @return null - если какая либо из коллекций null, или общая(all) коллекция пустая
     */
    fun <T>compareAllAndCurrent(all: MutableState<List<T>>?, current: MutableState<List<T>>?): MutableState<Map<T, Boolean>> {
        val comparedMap: MutableMap<T, Boolean> = mutableMapOf<T, Boolean>()
        val completedMap: MutableState<Map<T, Boolean>> = mutableStateOf(mapOf<T, Boolean>())

        if (all != null && current != null){
            if (all.value.isEmpty()){
                return completedMap
            }
            else{
                all.value.forEach { it ->
                    if (current.value.contains(it)){
                        comparedMap.put(it, true)
                    }
                    else{
                        comparedMap.put(it, false)
                    }
                }
                completedMap.value = comparedMap
            }
        }

        return completedMap
    }

    /**
     * Приводит LiveData к MutableState
     */
    fun <T> LiveData<List<T>>.toMutableStateList(lifecycleOwner: LifecycleOwner): MutableState<List<T>> {
        var stateData: MutableState<List<T>> = mutableStateOf(emptyList())
        this.observe(lifecycleOwner){data->
            if (data != null){
                stateData = mutableStateOf(data)
            }
        }
        return stateData
    }
    /**
     * Приводит LiveData к MutableState
     */
    fun <T,Y> LiveData<Map<T,Y>>.toMutableStateMap(lifecycleOwner: LifecycleOwner): MutableState<Map<T,Y>> {
        var stateData: MutableState<Map<T,Y>> = mutableStateOf(emptyMap())
        this.observe(lifecycleOwner){data->
            if (data != null){
                stateData = mutableStateOf(data)
            }
        }
        return stateData
    }
    /**
     * Приводит LiveData к MutableState
     */
    fun <T> LiveData<T>.toMutableState(lifecycleOwner: LifecycleOwner): MutableState<T> {
        var stateData: MutableState<T>? = null
        this.observe(lifecycleOwner){data->
            if (data != null){
                stateData = mutableStateOf(data)
            }
        }
        return stateData!!
    }


}