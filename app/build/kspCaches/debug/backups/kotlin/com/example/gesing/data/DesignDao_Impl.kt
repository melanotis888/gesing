package com.example.gesing.`data`

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DesignDao_Impl(
  __db: RoomDatabase,
) : DesignDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDesignConfig: EntityInsertAdapter<DesignConfig>

  private val __insertAdapterOfCustomFont: EntityInsertAdapter<CustomFont>
  init {
    this.__db = __db
    this.__insertAdapterOfDesignConfig = object : EntityInsertAdapter<DesignConfig>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `design_config` (`id`,`fontSize`,`fontFamily`,`textR`,`textG`,`textB`,`bgR`,`bgG`,`bgB`,`backgroundImageUri`,`iconName`,`iconR`,`iconG`,`iconB`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DesignConfig) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindDouble(2, entity.fontSize.toDouble())
        statement.bindText(3, entity.fontFamily)
        statement.bindLong(4, entity.textR.toLong())
        statement.bindLong(5, entity.textG.toLong())
        statement.bindLong(6, entity.textB.toLong())
        statement.bindLong(7, entity.bgR.toLong())
        statement.bindLong(8, entity.bgG.toLong())
        statement.bindLong(9, entity.bgB.toLong())
        val _tmpBackgroundImageUri: String? = entity.backgroundImageUri
        if (_tmpBackgroundImageUri == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpBackgroundImageUri)
        }
        statement.bindText(11, entity.iconName)
        statement.bindLong(12, entity.iconR.toLong())
        statement.bindLong(13, entity.iconG.toLong())
        statement.bindLong(14, entity.iconB.toLong())
      }
    }
    this.__insertAdapterOfCustomFont = object : EntityInsertAdapter<CustomFont>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `custom_fonts` (`id`,`name`,`fileName`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CustomFont) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.fileName)
      }
    }
  }

  public override suspend fun saveConfig(config: DesignConfig): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfDesignConfig.insert(_connection, config)
  }

  public override suspend fun insertCustomFont(font: CustomFont): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfCustomFont.insert(_connection, font)
  }

  public override fun getConfig(): Flow<DesignConfig?> {
    val _sql: String = "SELECT * FROM design_config WHERE id = 1"
    return createFlow(__db, false, arrayOf("design_config")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfFontSize: Int = getColumnIndexOrThrow(_stmt, "fontSize")
        val _cursorIndexOfFontFamily: Int = getColumnIndexOrThrow(_stmt, "fontFamily")
        val _cursorIndexOfTextR: Int = getColumnIndexOrThrow(_stmt, "textR")
        val _cursorIndexOfTextG: Int = getColumnIndexOrThrow(_stmt, "textG")
        val _cursorIndexOfTextB: Int = getColumnIndexOrThrow(_stmt, "textB")
        val _cursorIndexOfBgR: Int = getColumnIndexOrThrow(_stmt, "bgR")
        val _cursorIndexOfBgG: Int = getColumnIndexOrThrow(_stmt, "bgG")
        val _cursorIndexOfBgB: Int = getColumnIndexOrThrow(_stmt, "bgB")
        val _cursorIndexOfBackgroundImageUri: Int = getColumnIndexOrThrow(_stmt,
            "backgroundImageUri")
        val _cursorIndexOfIconName: Int = getColumnIndexOrThrow(_stmt, "iconName")
        val _cursorIndexOfIconR: Int = getColumnIndexOrThrow(_stmt, "iconR")
        val _cursorIndexOfIconG: Int = getColumnIndexOrThrow(_stmt, "iconG")
        val _cursorIndexOfIconB: Int = getColumnIndexOrThrow(_stmt, "iconB")
        val _result: DesignConfig?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpFontSize: Float
          _tmpFontSize = _stmt.getDouble(_cursorIndexOfFontSize).toFloat()
          val _tmpFontFamily: String
          _tmpFontFamily = _stmt.getText(_cursorIndexOfFontFamily)
          val _tmpTextR: Int
          _tmpTextR = _stmt.getLong(_cursorIndexOfTextR).toInt()
          val _tmpTextG: Int
          _tmpTextG = _stmt.getLong(_cursorIndexOfTextG).toInt()
          val _tmpTextB: Int
          _tmpTextB = _stmt.getLong(_cursorIndexOfTextB).toInt()
          val _tmpBgR: Int
          _tmpBgR = _stmt.getLong(_cursorIndexOfBgR).toInt()
          val _tmpBgG: Int
          _tmpBgG = _stmt.getLong(_cursorIndexOfBgG).toInt()
          val _tmpBgB: Int
          _tmpBgB = _stmt.getLong(_cursorIndexOfBgB).toInt()
          val _tmpBackgroundImageUri: String?
          if (_stmt.isNull(_cursorIndexOfBackgroundImageUri)) {
            _tmpBackgroundImageUri = null
          } else {
            _tmpBackgroundImageUri = _stmt.getText(_cursorIndexOfBackgroundImageUri)
          }
          val _tmpIconName: String
          _tmpIconName = _stmt.getText(_cursorIndexOfIconName)
          val _tmpIconR: Int
          _tmpIconR = _stmt.getLong(_cursorIndexOfIconR).toInt()
          val _tmpIconG: Int
          _tmpIconG = _stmt.getLong(_cursorIndexOfIconG).toInt()
          val _tmpIconB: Int
          _tmpIconB = _stmt.getLong(_cursorIndexOfIconB).toInt()
          _result =
              DesignConfig(_tmpId,_tmpFontSize,_tmpFontFamily,_tmpTextR,_tmpTextG,_tmpTextB,_tmpBgR,_tmpBgG,_tmpBgB,_tmpBackgroundImageUri,_tmpIconName,_tmpIconR,_tmpIconG,_tmpIconB)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCustomFonts(): Flow<List<CustomFont>> {
    val _sql: String = "SELECT * FROM custom_fonts"
    return createFlow(__db, false, arrayOf("custom_fonts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfFileName: Int = getColumnIndexOrThrow(_stmt, "fileName")
        val _result: MutableList<CustomFont> = mutableListOf()
        while (_stmt.step()) {
          val _item: CustomFont
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpFileName: String
          _tmpFileName = _stmt.getText(_cursorIndexOfFileName)
          _item = CustomFont(_tmpId,_tmpName,_tmpFileName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
