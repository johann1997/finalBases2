/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class LoginController implements Initializable {
    @FXML
    private TextField loginUser;
    
    @FXML
    private PasswordField loginPassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LogIn(ActionEvent event) {
        
        String usuario = loginUser.getText();
        String contrasena = loginPassword.getText();
        Conectar cd = new Conectar();
        
        
         Usuario ObjU= null;
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                String sql = "call log_in('"+usuario+"','"+contrasena+"')";
                ResultSet rs = st.executeQuery(sql);
             
                if(rs.next()){
                    //carga usuario
                    String usuarioA = rs.getString("user_log");
                    String contrasenaA = rs.getString("password_log");
                    
                     ObjU = new Usuario();
                    //manda a nueva pestaña
                    cambiarPestana(event, "Inicio");
                }else{
                    //no inicia sesion
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña erronea");
                }
                
                
                
                
                
                
                
                 try {
            FXMLLoader buscar_trabajador_load = new FXMLLoader(getClass().getResource("/Vista/Inicio.fxml"));
            Parent buscar_trabajador_parent = (Parent) buscar_trabajador_load.load();
            Scene buscar_trabajos_scene = new Scene(buscar_trabajador_parent);
//            InicioController ic = (InicioController) buscar_trabajador_load.getController();
            
            
            
            
            
     AddCertificadoController acc = (AddCertificadoController) buscar_trabajador_load.getController();
     
     BuscarProyectoController bpc = (BuscarProyectoController) buscar_trabajador_load.getController();

     BuscarTrabajadorController btc = (BuscarTrabajadorController) buscar_trabajador_load.getController();

//     Conectar c = (Conectar) buscar_trabajador_load.getController();
     
     EmpresasController ec = (EmpresasController) buscar_trabajador_load.getController();
     
     InicioController ic = (InicioController) buscar_trabajador_load.getController();
     
     InsertarProyectoController ipc = (InsertarProyectoController) buscar_trabajador_load.getController();
     
//     ProponentesController pc = (ProponentesController) buscar_trabajador_load.getController();
     
     RegistroController rc = (RegistroController) buscar_trabajador_load.getController();
     
     UsEmpresaController uec = (UsEmpresaController) buscar_trabajador_load.getController();

            
                  
             acc.importarVariables(ObjU, contrasena);
             bpc.importarVariables(ObjU, contrasena);
             btc.importarVariables(ObjU, contrasena);
//             c.importarVariables(ObjU, contrasena);
             ec.importarVariables(ObjU, contrasena);
             ic.importarVariables(ObjU, contrasena);
             ipc.importarVariables(ObjU, contrasena);
//             pc.importarVariables(ObjU, contrasena);
             rc.importarVariables(ObjU, contrasena);
             uec.importarVariables(ObjU, contrasena);

                      
                  

            Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            main_stage.setScene(buscar_trabajos_scene);
            main_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

      
                   
           }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    @FXML
    private void irRegistro(ActionEvent event) {
                    cambiarPestana(event, "Registro");
    }
    
    void cambiarPestana(ActionEvent event,String url){
        try {
            Parent buscar_trabajador_parent = FXMLLoader.load(getClass().getResource("/Vista/"+url+".fxml"));
            Scene buscar_trabajos_scene = new Scene(buscar_trabajador_parent);
            Stage main_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            main_stage.setScene(buscar_trabajos_scene);
            main_stage.show();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
