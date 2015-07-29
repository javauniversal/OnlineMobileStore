package com.example.poo_code.onlinemobilestore.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.poo_code.onlinemobilestore.Entities.Product;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlPedido = "CREATE TABLE carrito (id integer primary key AUTOINCREMENT, codeproduct int, nameProduct text, " +
                           "                        precio REAL, descripcion text, urlimagen text)";

        db.execSQL(sqlPedido);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS carrito");
        this.onCreate(db);

    }


    public boolean insertProduct(Product data){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put("codeproduct", data.getIdProduct());
            values.put("nameProduct", data.getNombre());
            values.put("precio", data.getPrecio());
            values.put("descripcion", data.getDescripcion());
            values.put("urlimagen", data.getImagemes());

            db.insert("carrito", null, values);
            Log.d("carrito", data.toString());
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to insert word,", e);
            return false;
        }

        return true;
    }


    public List<Product> getProductCar() {
        ArrayList<Product> addProduct = new ArrayList<>();
        String sql = "SELECT * FROM carrito";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Product productCar;
        if (cursor.moveToFirst()) {
            do {
                productCar = new Product();
                productCar.setIdAutoIncrement(Integer.parseInt(cursor.getString(0)));
                productCar.setIdProduct(Integer.parseInt(cursor.getString(1)));
                productCar.setNombre(cursor.getString(2));
                productCar.setPrecio(Double.parseDouble(cursor.getString(3)));
                productCar.setDescripcion(cursor.getString(4));
                productCar.setImagemes(cursor.getString(5));
                addProduct.add(productCar);
            } while(cursor.moveToNext());
        }
        return addProduct;
    }

    public boolean DeleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("carrito", "id = ? ", new String[] {String.valueOf(id)});
        db.close();
        return p > 0;
    }

    /*
    public Cursor getEstudiantesSp() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT id AS _id, nombre FROM estudiantes";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public Cursor getMateriasSp(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT materias.id AS _id, materias.nombre AS nombre FROM materias WHERE materias.id NOT IN (SELECT idMateria FROM movimiento WHERE idEstuden = "+id+" )";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public boolean insetMovimiento(EntityMovimiento data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idEstuden", data.getIdEstudiante());
        values.put("jornada", data.getJornada());
        values.put("facultad", data.getFacultad());
        values.put("semestre", data.getSemestre());
        values.put("idMateria", data.getIdMateria());

        db.insert("movimiento", null, values);
        Log.d("Movimiento", data.toString());
        db.close();
        return true;
    }

    public void insertMaterias(EntityMateria data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", data.getIdMateria());
        values.put("nombre", data.getDescripcion());
        values.put("estado", data.getEstado());

        db.insert("materias", null, values);
        Log.d("materias", data.toString());
        db.close();
    }

    public ArrayList<EntityMateria> getMaterias() {
        ArrayList<EntityMateria> materias = new ArrayList<EntityMateria>();

        String sql = "SELECT * FROM materias";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        EntityMateria materia = null;
        if (cursor.moveToFirst()) {
            do {
                materia = new EntityMateria();
                materia.setIdMateria(Integer.parseInt(cursor.getString(0)));
                materia.setDescripcion(cursor.getString(1));
                materia.setEstado(Integer.parseInt(cursor.getString(2)));
                materias.add(materia);
            } while(cursor.moveToNext());
        }
        return materias;
    }

    public boolean validarExistencia(int id, String tabla){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        boolean indicador = false;
        if(tabla == "estudiante"){
            String columns[] = {"id", "cedula", "nombre", "apellido", "correo", "telefono", "edad", "estado", "sexo"};
            cursor = db.query("estudiantes", columns, "id = ?", new String[] {String.valueOf(id)}, null, null, null, null);
            if (cursor.getCount() <= 0){
                indicador = true;
            }
        }else if(tabla == "materias"){

            String columns[] = {"id", "nombre", "estado"};
            cursor = db.query("materias", columns, "id = ?", new String[] {String.valueOf(id)}, null, null, null, null);
            if (cursor.getCount() <= 0){
                indicador = true;
            }
        }
        return indicador;
    }

    public boolean eliminarMateria(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int p = db.delete("materias", "id = ?", new String[] {String.valueOf(id)});
        db.close();
        if(p <= 0){
            return false;
        }
        return true;
    }

    public boolean updateEstudiante(EntityEstudiante data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", data.getNombre());
        values.put("apellido", data.getApellido());
        values.put("correo", data.getCorreo());
        values.put("telefono", data.getTelefono());
        values.put("edad", data.getEdad());

        try {
            db.update("estudiantes", values, "id = ?", new String[] {String.valueOf(data.getCodigo())});
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to update word,", e);
            return false;
        }

        return true;
    }

    public boolean updateMateria(EntityMateria data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", data.getDescripcion());

        try {
            db.update("materias", values, "id = ?", new String[] {String.valueOf(data.getIdMateria())});
            db.close();
        }catch (SQLiteConstraintException e){
            Log.d("data", "failure to update word,", e);
            return false;
        }

        return true;
    }
    */

}

