package com.example.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlite.model.Product;
import com.example.sqlite.model.ProductType;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductSqlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Product.db";
    public static final String PRODUCT_TYPE_TABLE_NAME = "product_type";

    public static final String PRODUCT_TYPE_ID = "typeID";
    public static final String PRODUCT_TYPE_NAME = "typeName";


    public static final String PRODUCT_TABLE_NAME = "product";

    public static final String PRODUCT_ID = "productID";
    public static final String PRODUCT_NAME = "productName";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_DATE = "date";
    public static final String PRODUCT_COUNT = "count";


    private HashMap hp;

    public ProductSqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE " + PRODUCT_TYPE_TABLE_NAME + " ( " + PRODUCT_TYPE_ID + " TEXT  PRIMARY KEY , " + PRODUCT_TYPE_NAME + " TEXT)");

        db.execSQL("CREATE TABLE "
                + PRODUCT_TABLE_NAME + "(" + PRODUCT_ID + " TEXT  PRIMARY KEY , " + PRODUCT_NAME + " TEXT , " + PRODUCT_TYPE_ID + " TEXT , " +
                PRODUCT_PRICE + " INTEGER , " + PRODUCT_DATE + " TEXT," + PRODUCT_COUNT + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS product_type");
        db.execSQL("DROP TABLE IF EXISTS product");
        onCreate(db);
    }

    public boolean insertProductType(ProductType productType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_TYPE_ID, productType.getTypeID());
        contentValues.put(PRODUCT_TYPE_NAME, productType.getTypeName());
        db.insert(PRODUCT_TYPE_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<ProductType>   getAllProductType() {

        ArrayList<ProductType> list = new ArrayList<ProductType>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + PRODUCT_TYPE_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        ProductType obj = new ProductType();
                        //only one column
                        obj.setTypeID(cursor.getString(0));
                        obj.setTypeName(cursor.getString(1));

                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public boolean updateProductType(ProductType productType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_TYPE_NAME, productType.getTypeName());
        db.update(PRODUCT_TYPE_TABLE_NAME, contentValues, PRODUCT_TYPE_ID + " = ? ", new String[]{productType.getTypeID()});
        return true;
    }

    public Integer deleteProductType(ProductType productType) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PRODUCT_TYPE_TABLE_NAME,
                PRODUCT_TYPE_ID +
                        " = ? ",
                new String[]{productType.getTypeID()});
    }


    public boolean insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product.getProductID());
        contentValues.put(PRODUCT_NAME, product.getProductName());
        contentValues.put(PRODUCT_TYPE_ID, product.getProductType());
        contentValues.put(PRODUCT_PRICE, product.getPrice());
        contentValues.put(PRODUCT_DATE, product.getDate());
        contentValues.put(PRODUCT_COUNT, product.getCount());

        db.insert(PRODUCT_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Product> getAllProduct() {

        ArrayList<Product> list = new ArrayList<Product>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + PRODUCT_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Product obj = new Product();
                        //only one column
                        obj.setProductID(cursor.getString(0));
                        obj.setProductName(cursor.getString(1));
                        obj.setProductType(cursor.getString(2));
                        obj.setPrice(cursor.getInt(3));
                        obj.setDate(cursor.getString(4));
                        obj.setCount(cursor.getInt(5));

                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public ArrayList<Product> getAllProductByDate(String start, String end) {

        ArrayList<Product> list = new ArrayList<Product>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + PRODUCT_TABLE_NAME + " WHERE `date` >= " + android.database.DatabaseUtils.sqlEscapeString(start) + " AND `date` <= " + android.database.DatabaseUtils.sqlEscapeString(end);
        Log.d("hunghkp", "getAllProductByDate: " + selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Product obj = new Product();
                        //only one column
                        obj.setProductID(cursor.getString(0));
                        obj.setProductName(cursor.getString(1));
                        obj.setProductType(cursor.getString(2));
                        obj.setPrice(cursor.getInt(3));
                        obj.setDate(cursor.getString(4));
                        obj.setCount(cursor.getInt(5));

                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {
                }
            }

        } finally {
            try {
                db.close();
            } catch (Exception ignore) {
            }
        }

        return list;
    }

    public boolean updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_ID, product.getProductID());
        contentValues.put(PRODUCT_NAME, product.getProductName());
        contentValues.put(PRODUCT_TYPE_ID, product.getProductType());
        contentValues.put(PRODUCT_PRICE, product.getPrice());
        contentValues.put(PRODUCT_DATE, product.getDate());
        contentValues.put(PRODUCT_COUNT, product.getCount());
        db.update(PRODUCT_TABLE_NAME, contentValues, PRODUCT_ID + " = ? ", new String[]{product.getProductID()});
        return true;
    }

    public Integer deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PRODUCT_TABLE_NAME,
                PRODUCT_ID +
                        " = ? ",
                new String[]{product.getProductID()});
    }

}