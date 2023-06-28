package br.com.tarcisiofl.bookshelf.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class Converters(private val moshi: Moshi) {

    @TypeConverter
    fun jsonToPersonEntityList(json: String?): List<PersonEntity> {
        if (json == null) return emptyList()
        val listType = Types.newParameterizedType(List::class.java, PersonEntity::class.java)
        val adapter: JsonAdapter<List<PersonEntity>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

    @TypeConverter
    fun personEntityListToJson(personEntityList: List<PersonEntity>): String {
        val listType = Types.newParameterizedType(List::class.java, PersonEntity::class.java)
        val adapter: JsonAdapter<List<PersonEntity>> = moshi.adapter(listType)
        return adapter.toJson(personEntityList)
    }

    @TypeConverter
    fun jsonToStringList(json: String?): List<String> {
        if (json == null) return emptyList()
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

    @TypeConverter
    fun stringListToJson(stringList: List<String>): String {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.toJson(stringList)
    }

    @TypeConverter
    fun jsonToMap(json: String?): Map<String, String> {
        if (json == null) return emptyMap()
        val listType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val adapter: JsonAdapter<Map<String, String>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyMap()
    }

    @TypeConverter
    fun stringMapToJson(mapper: Map<String, String>): String {
        val listType = Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val adapter: JsonAdapter<Map<String, String>> = moshi.adapter(listType)
        return adapter.toJson(mapper)
    }
}