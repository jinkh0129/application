package com.sungdonggu.naverapi.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


data class Memo(var no: Long?, var content: String, var datetime: Long)


/**SQLite를 사용할 수 있는 도구가 되는 클래스를 안드로이드에서 제공하는데
 * 그 이름이 SQLiteOpenHelper클래스이다.**/
class SqliteHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    /**SQLiteOpenHelper를 생성할 때 안에 넣어줘야 하는 항목들로는
     * context, DB이름, null, 그리고 버전값이다.*/


    /**2개의 멤버메서드를 구현해줘야한다.**/
    override fun onCreate(db: SQLiteDatabase?) {
        /**처음에 호출할 때 DB가 없으면 onCreate를 실행하여 새로 생성해주고**/

        /**쿼리문을 생성해준다.*/
        val create = "CREATE TABLE memo (`no` INTEGER PRIMARY KEY, content TEXT, datetime INTEGER)"
        /**쿼리문을 실행하려면 execute를 해야한다.*/
        // 쿼리문을 실행하는 함수에는 rawQuery도 있는데 둘의 차이점은 execute는 반환값이 없다는 점이다.
        db?.execSQL(create)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /**호출했을 때 DB가 이미 존재하면 onUpgrade를 실행한다.**/
        /**테이블에 변경사항이 있을 경우 호출됨**/
        /**SQLiteHelper()의 생성자를 호출할 때 기존 데이터베이스와의 version
         * 비교해서 더 높으면 호출되는 형태이다.**/
    }


    /**(CRUD)insert 함수*/
    /**생성자 안에 직접 content,datetime 정보를 입력해줘도 되지만
     * data class를 만들어서 클래스를 넣어주는 것이 더욱 깔끔한 방법**/
    fun insertMemo(memo: Memo) {
        /**insert를 할 때는 contentValues를 사용한다.*/
        /**data를 쓸 때는 DB를 가져와야 하는데 아래와 같이 가져온다*/
        /**순서는 db가져오기---> db넣기---> db 닫기*/
        val wd = writableDatabase

        /**Memo를 입력타입으로 변환해주는 것이다.**/
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        wd.insert("memo", null, values)// 넣을 때는 테이블이름, null, 값 순서대로 넣어주면 된다.
        wd.close()
    }

    /**(CRUD)조회 함수*/
    fun selectMemo(): MutableList<Memo> {
        val list = mutableListOf<Memo>() // 반환값이 있으면 반환값의 타입을 갖는 것을 하나 만들어주는 것이 팁이다.
        // 쿼리문 만들기
        val select = "SELECT * FROM memo"
        var rd = readableDatabase
        // 쿼리문을 실행할 때 쓰이는 것 중 하나인 rawQuery는 반환값이 존재하는데
        // cursor라는 형태로 반환이 된다.
        val cursor = rd.rawQuery(select, null)
        while (cursor.moveToNext()) {
            /*어떤 칼럼을 가져올지 알려줘야 한다.*/
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))

            /**여기까지가  DB의 1줄을 가져온 것이다.*/

            val memo = Memo(no, content, datetime)
            list.add(memo)
        }
        cursor.close()
        rd.close()
        //리턴하기 전에 커서와 DB를 닫아준다.
        return list
    }

    fun updateMemo(memo: Memo) {
        val wd = writableDatabase

        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        wd.update("memo", values, "no = ${memo.no}",null)
        wd.close()
    }


    fun deleteMemo(memo: Memo){
        val delete = "DELETE FROM memo WHERE no=${memo.no}"

        val wd = writableDatabase
        wd.execSQL(delete)
        wd.close()
    }

}