package com.clay.qrscan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseDeviceMessage extends SQLiteOpenHelper {

    private final static String TABLE_NAME = "DeviceMessageTable";//表的名称
    private final static String ID = "id"; //首列的名称
    private SQLiteDatabase db = null;

    public final static String DeviceClientID = "deviceClientID";//设备的DeviceClientID
    public final static String DeviceName = "deviceName";//设备的名字
    public final static String DeviceSwitchStatus = "deviceSwitchStatus";//开关的状态
    public final static String OtherData1 = "otherData1";//备用
    public final static String OtherData2 = "otherData2";//备用


    public DataBaseDeviceMessage(Context context,String DATABASE_NAME,int DATABASE_VERSION) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER primary key autoincrement, " +
                DeviceClientID + " text, "+
                DeviceName + " text, "+
                DeviceSwitchStatus + " text, "+
                OtherData1 + " text, "+
                OtherData2 + " text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }


    /**
     * 插入
     * @param deviceClientID
     * @param deviceName
     * @param deviceSwitchStatus
     * @param otherData1
     * @param otherData2
     * @return
     */
    public long insert(String deviceClientID,String deviceName,String deviceSwitchStatus,
                       String otherData1,String otherData2)
    {
        db = getWritableDatabase();
        /* ContentValues */
        ContentValues cv = new ContentValues();
        cv.put(DeviceClientID, deviceClientID);
        cv.put(DeviceName, deviceName);
        cv.put(DeviceSwitchStatus, deviceSwitchStatus);
        cv.put(OtherData1, otherData1);
        cv.put(OtherData2, otherData2);

        long row = db.insert(TABLE_NAME, null, cv);
        db.close();
        return row;
    }

    /**
     * 删除操作
     * @param deviceClientID	根据deviceClientID删除数据
     * @return
     */
    public long delete(String deviceClientID)
    {
        db = getWritableDatabase();
        String where = DeviceClientID + " = ?";
        String[] whereValue ={deviceClientID};
        long row = db.delete(TABLE_NAME, where, whereValue);
        db.close();
        return row;
    }


    /**
     * 更新数据
     * @param deviceClientID
     * @param deviceName
     * @param deviceSwitchStatus
     * @param otherData1
     * @param otherData2
     * @return
     */
    public long update(String deviceClientID,String deviceName,String deviceSwitchStatus,
                       String otherData1,String otherData2)
    {
        db = getWritableDatabase();
        String where = DeviceClientID + " = ?";
        String[] whereValue = {deviceClientID};

        ContentValues cv = new ContentValues();
        if (deviceName!=null) {
            cv.put(DeviceName, deviceName);
        }
        if (deviceName!=null) {
            cv.put(DeviceName, deviceName);
        }
        if (deviceSwitchStatus!=null) {
            cv.put(DeviceSwitchStatus, deviceSwitchStatus);
        }
        if (otherData1!=null) {
            cv.put(OtherData1, otherData1);
        }
        if (otherData2!=null) {
            cv.put(OtherData2, otherData2);
        }


        long row = db.update(TABLE_NAME, cv, where, whereValue);
        db.close();
        return row;
    }

    /**
     * 查询数据
     * @param QueryContent0
     * @param queryContent0
     * @param QueryContent1
     * @param queryContent1
     * @param QueryContent2
     * @param queryContent2
     * @param QueryContent3
     * @param queryContent3
     * @return
     */
    public Cursor query(String QueryContent0,String queryContent0
            ,String QueryContent1,String queryContent1,String QueryContent2,String queryContent2
            ,String QueryContent3,String queryContent3)
    {
        db = getWritableDatabase();
        Cursor cursor = null;
        String str = null;
        //查询
        if(QueryContent3!=null && queryContent3!=null &&QueryContent0!=null && queryContent0!=null && QueryContent1!=null && queryContent1!=null
                && QueryContent2!=null && queryContent2!=null)
        {
            str = "select * "+" from "+ TABLE_NAME +" where "+ QueryContent3 +" = ? "+" and "+
                    QueryContent0 +" = ? "+" and "+ QueryContent1 +" = ? "+" and "+ QueryContent2 +" = ? ";
            cursor = db.rawQuery(str, new String[]{queryContent3,queryContent0,queryContent1,queryContent2});
        }
        else if (QueryContent0!=null && queryContent0!=null && QueryContent1!=null && queryContent1!=null
                && QueryContent2!=null && queryContent2!=null)
        {
            str = "select * "+" from "+ TABLE_NAME +" where "+
                    QueryContent0 +" = ? "+" and "+ QueryContent1 +" = ? "+" and "+ QueryContent2 +" = ? ";
            cursor = db.rawQuery(str, new String[]{queryContent0,queryContent1,queryContent2});
        }
        else if (QueryContent0!=null && queryContent0!=null && QueryContent1!=null && queryContent1!=null) {
            str = "select *"+" from "+ TABLE_NAME +" where "+ QueryContent0 +" =  ? "+" and "+ QueryContent1 +" = ? ";
            cursor = db.rawQuery(str, new String[]{queryContent0,queryContent1});
        }
        else if (QueryContent0 != null && queryContent0 !=null) {
            str = "select *"+" from "+ TABLE_NAME +" where "+ QueryContent0 +" =  ? ";
            cursor = db.rawQuery(str, new String[]{queryContent0});
        }
        else if (TABLE_NAME!=null) {
            str = "select * from "+ TABLE_NAME;
            cursor = db.rawQuery(str, null);
        }
        return cursor;
    }
}
