package com.example.gesing.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _designDao: Lazy<DesignDao> = lazy {
    DesignDao_Impl(this)
  }


  public override val designDao: DesignDao
    get() = _designDao.value

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(5,
        "3c0e4e40b7065df5712bfeeae86f3b32", "4f548c2dd29887319d06d393a9d6509d") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `design_config` (`id` INTEGER NOT NULL, `fontSize` REAL NOT NULL, `fontFamily` TEXT NOT NULL, `textR` INTEGER NOT NULL, `textG` INTEGER NOT NULL, `textB` INTEGER NOT NULL, `bgR` INTEGER NOT NULL, `bgG` INTEGER NOT NULL, `bgB` INTEGER NOT NULL, `backgroundImageUri` TEXT, `iconName` TEXT NOT NULL, `iconR` INTEGER NOT NULL, `iconG` INTEGER NOT NULL, `iconB` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `custom_fonts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `fileName` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3c0e4e40b7065df5712bfeeae86f3b32')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `design_config`")
        connection.execSQL("DROP TABLE IF EXISTS `custom_fonts`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsDesignConfig: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDesignConfig.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("fontSize", TableInfo.Column("fontSize", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("fontFamily", TableInfo.Column("fontFamily", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("textR", TableInfo.Column("textR", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("textG", TableInfo.Column("textG", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("textB", TableInfo.Column("textB", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("bgR", TableInfo.Column("bgR", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("bgG", TableInfo.Column("bgG", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("bgB", TableInfo.Column("bgB", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("backgroundImageUri", TableInfo.Column("backgroundImageUri",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("iconName", TableInfo.Column("iconName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("iconR", TableInfo.Column("iconR", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("iconG", TableInfo.Column("iconG", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDesignConfig.put("iconB", TableInfo.Column("iconB", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDesignConfig: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDesignConfig: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDesignConfig: TableInfo = TableInfo("design_config", _columnsDesignConfig,
            _foreignKeysDesignConfig, _indicesDesignConfig)
        val _existingDesignConfig: TableInfo = read(connection, "design_config")
        if (!_infoDesignConfig.equals(_existingDesignConfig)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |design_config(com.example.gesing.data.DesignConfig).
              | Expected:
              |""".trimMargin() + _infoDesignConfig + """
              |
              | Found:
              |""".trimMargin() + _existingDesignConfig)
        }
        val _columnsCustomFonts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCustomFonts.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomFonts.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCustomFonts.put("fileName", TableInfo.Column("fileName", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCustomFonts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCustomFonts: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCustomFonts: TableInfo = TableInfo("custom_fonts", _columnsCustomFonts,
            _foreignKeysCustomFonts, _indicesCustomFonts)
        val _existingCustomFonts: TableInfo = read(connection, "custom_fonts")
        if (!_infoCustomFonts.equals(_existingCustomFonts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |custom_fonts(com.example.gesing.data.CustomFont).
              | Expected:
              |""".trimMargin() + _infoCustomFonts + """
              |
              | Found:
              |""".trimMargin() + _existingCustomFonts)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "design_config", "custom_fonts")
  }

  public override fun clearAllTables() {
    super.performClear(false, "design_config", "custom_fonts")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(DesignDao::class, DesignDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }
}
