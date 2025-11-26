import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analizador {

    private List<FilaSimbolo> tablaSimbolos;
    private Map<String, Integer> mapaIdsAsignados;
    private Map<String, String> mapaTiposDeclarados;
    private int contadorIdProgramador;

    public Analizador() {
        this.tablaSimbolos = new ArrayList<>();
        this.mapaIdsAsignados = new HashMap<>();
        this.mapaTiposDeclarados = new HashMap<>();
        this.contadorIdProgramador = 1;
    }

    public List<FilaSimbolo> analizar(String codigo) {
        limpiarMemoria();
        
        String codigoSinComentarios = codigo.replaceAll("//.*", "");

        String codigoProcesado = preProcesarCodigo(codigoSinComentarios);
        String[] palabras = codigoProcesado.trim().split("\\s+");

        String ultimoTipoVisto = null;

        for (String palabra : palabras) {
            if (palabra.isEmpty()) continue;
            
            palabra = palabra.replace("__ESPACIO__", " "); 
            
            String token = LibreriaToken.getToken(palabra);      

            String id = "";
            String lexemaVisual = palabra;
            
            String tipoSemantico = null;
            boolean esDeclaracion = false;

            if (token != null) {
                if (token.equals("TD") || token.equals("STR") || token.equals("TM")) 
                    ultimoTipoVisto = palabra;
                else if (token.equals("CL")) 
                    ultimoTipoVisto = "class";
                else if (token.equals("PCK")) 
                    ultimoTipoVisto = "package";
                else if (token.equals("FI") || token.equals("LLC") || token.equals("LLA")) 
                    ultimoTipoVisto = null;
                else if (token.equals("FI") || token.equals("LLC") || 
                         token.equals("LLA") || token.equals("ODA"))
                    // Reinicio de los chidos
                    ultimoTipoVisto = null;
            }
            else {
                if (LibreriaToken.PATRON_ID.matcher(palabra).matches()) {
                    token = "ID";

                    if (mapaIdsAsignados.containsKey(palabra)) {
                        int idNum = mapaIdsAsignados.get(palabra);
                        id = "";
                        lexemaVisual = "ID" + idNum;
                        tipoSemantico = mapaTiposDeclarados.get(palabra);
                    } else {
                        int nuevoId = contadorIdProgramador++;
                        mapaIdsAsignados.put(palabra, nuevoId);

                        id = String.valueOf(nuevoId);
                        lexemaVisual = palabra;
                        tipoSemantico = (ultimoTipoVisto != null) ? ultimoTipoVisto : "Desconocido";
                        mapaTiposDeclarados.put(palabra, tipoSemantico);
                        esDeclaracion = true;
                    }
                    
                    ultimoTipoVisto = palabra;
                    
                }
                else if (LibreriaToken.PATRON_ENTERO.matcher(palabra).matches()) token = "VTE";
                else if (LibreriaToken.PATRON_DOUBLE.matcher(palabra).matches()) token = "VTD";
                else if (LibreriaToken.PATRON_FLOAT.matcher(palabra).matches()) token = "VTF";
                else if (LibreriaToken.PATRON_CARACTER.matcher(palabra).matches()) token = "VTC";
                else if (LibreriaToken.PATRON_STRING.matcher(palabra).matches()) token = "VTS";
                else token = "ERROR";
            }

            tablaSimbolos.add(new FilaSimbolo(id, lexemaVisual, token, tipoSemantico, esDeclaracion));
        }
        return tablaSimbolos;
    }

    private void limpiarMemoria() {
        tablaSimbolos.clear();
        mapaIdsAsignados.clear();
        mapaTiposDeclarados.clear();
        contadorIdProgramador = 1;
    }

    private String preProcesarCodigo(String codigo) {
        StringBuilder sb = new StringBuilder();
        boolean dentroDeString = false;
        int i = 0;
        while (i < codigo.length()) {
            char letra = codigo.charAt(i);

            if (letra == '"') {
                dentroDeString = !dentroDeString;
            }

            if (dentroDeString && letra == ' ') {
                sb.append("__ESPACIO__");
            } else {
                sb.append(letra);
            }
            i++;
        }
        
        String cadenasPRotegidas = sb.toString();

        // Espaco para operadores y símbolos simples   
        String procesado = cadenasPRotegidas
                .replace(";", " ; ")
                .replace("=", " = ")
                .replace("+", " + ")
                .replace("-", " - ")
                .replace("*", " * ")
                .replace("/", " / ")
                .replace("(", " ( ")
                .replace(")", " ) ")
                .replace("{", " { ")
                .replace("}", " } ")
                .replace("[", " [ ")
                .replace("]", " ] ")
                .replace("<", " < ")
                .replace(">", " > ")
                .replace(".", " . ")
                .replace(",", " , ")
                .replace(":", " : ")
                .replace("?", " ? ")
                ;

        procesado = procesado
                .replace("=  =", "==")
                .replace("+  +", "++")
                .replace("-  -", "--")
                .replace("& &", "&&")
                .replace("| |", "||")
        ;

        procesado = procesado.replaceAll("([0-9]+)\\s+\\.\\s+([0-9]+[f]?)", "$1.$2");
        
        return procesado;
    }
    
    
}