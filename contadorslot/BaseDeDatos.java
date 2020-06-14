
package contadorslot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Caos5000
 */
public class BaseDeDatos {
    public static Connection connect() {

        // parámetro BD
        String url = "jdbc:sqlite:Carreras.db";
        Connection conn = null;
        // Creando conexión a la BD
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static String crearTablas(String nomCarrera) {

        String sql = "CREATE TABLE IF NOT EXISTS "+nomCarrera+" (\n"
                + "	Piloto PRIMARY KEY,\n"
                + "	Pista number NOT NULL,\n"
                + "	Vueltas number NOT NULL,\n"
                + "	Posicion number NOT NULL\n"
                + ");";
        
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                conn.close();
                return "Carrera creada en la base de datos.";
        } catch (SQLException e) {
            return "Error al crear la carrera";
        }
        
    }
    public static String añadirPiloto(String nomCarrera,String nombre,int pista,int vueltas,int posicion) {
        String sql = "INSERT INTO "+nomCarrera+"(Piloto,Pista,Vueltas,Posicion) VALUES(?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2,pista);
            pstmt.setInt(3,vueltas);
            pstmt.setInt(4,posicion);
            pstmt.executeUpdate();
            conn.close();
            return "Piloto "+nombre+" añadido";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Error\nLa pista esta repetida";
    }
    
    public static String[] verResultado(String nomCarrera){
        String piloto;
        int pista,vueltas,posicion;
        String sql = "SELECT * FROM "+nomCarrera;
        String [] results ={"","","","","","","",""};
        String [] error = {""};
        try{Connection conn= connect();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    piloto = rs.getString("Piloto");
                    pista = rs.getInt("Pista");
                    vueltas = rs.getInt("Vueltas");
                    posicion = rs.getInt("Posicion");
                    switch(pista){
                        case 1:
                            results[0]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 2:
                            results[1]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 3:
                            results[2]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 4:
                            results[3]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 5:
                            results[4]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 6:
                            results[5]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 7:
                            results[6]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                        case 8:
                            results[7]="Nombre: "+piloto+"\nPista: "+pista+"\nVueltas: "+vueltas+"\nPosición:"+posicion;
                            break;
                    }
                }
                conn.close();
                return results;
                
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return error;
    }
}

