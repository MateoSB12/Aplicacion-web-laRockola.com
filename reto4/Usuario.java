package reto4;
import java.util.*;

public class Usuario {
    ArrayList<String>listatelefonos;
    public Usuario(){
        listatelefonos=new ArrayList<String>();
    }
    public void agregartelefono(String telefono){
        if(listatelefonos.indexOf(telefono)==-1)
        listatelefonos.add(telefono);
    }
    public void mostrartelefono(){
        //System.out.print("{");
        for(int i=0;i<listatelefonos.size();i++){
        listatelefonos.get(i);
        }
        //System.out.print("}");
        Collections.sort(listatelefonos);
        System.out.println(listatelefonos);
    }
}
