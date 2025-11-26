import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LibreriaToken {

    private static final Map<String, String> tokenMap = new HashMap<>();

    // Expresiones regulares
    public static final Pattern PATRON_ID = Pattern.compile("^[a-zA-Z_$][a-zA-Z0-9_$]*$");  // ID
    public static final Pattern PATRON_ENTERO = Pattern.compile("^[0-9]+$");                // VTE
    public static final Pattern PATRON_FLOAT = Pattern.compile("^[0-9]+(\\.[0-9]+)?[f]$"); // VTF
    public static final Pattern PATRON_DOUBLE = Pattern.compile("^[0-9]+\\.[0-9]+$");      // VTD
    public static final Pattern PATRON_CARACTER = Pattern.compile("^'.'$");                 // VTC
    public static final Pattern PATRON_STRING = Pattern.compile("^\".*\"$");                // VTS

    static {
        // Tipos de Datos (TD)
        tokenMap.put("int", "TD");
        tokenMap.put("boolean", "TD");
        tokenMap.put("double", "TD");
        tokenMap.put("float", "TD");
        tokenMap.put("long", "TD");
        tokenMap.put("char", "TD");
        tokenMap.put("byte", "TD");
        tokenMap.put("short", "TD");
        tokenMap.put("String", "STR");

        // Modificadores de Acceso
        tokenMap.put("public", "MA");
        tokenMap.put("private", "MA");
        tokenMap.put("protected", "MA");
        tokenMap.put("abstract", "MA");
        tokenMap.put("static", "ST");
        tokenMap.put("final", "TDD");

        // Palabras Reservadas
        tokenMap.put("if", "PRIF");
        tokenMap.put("else", "PRE");
        tokenMap.put("while", "PRW");
        tokenMap.put("for", "PRF");
        tokenMap.put("switch", "PRSW");
        tokenMap.put("case", "CS");
        tokenMap.put("break", "BR");
        tokenMap.put("return", "RTN");
        tokenMap.put("class", "CL");
        tokenMap.put("void", "TM");
        tokenMap.put("new", "NW");
        tokenMap.put("this", "RF");
        tokenMap.put("super", "SPR");
        tokenMap.put("import", "IMP");
        tokenMap.put("package", "PCK");
        tokenMap.put("null", "_NU");
        tokenMap.put("main", "PMAIN");

        // Booleanos
        tokenMap.put("true", "VTB");
        tokenMap.put("false", "VTB");

        // Operadores y Símbolos
        tokenMap.put("=", "ODA");
        tokenMap.put("+", "OPA");
        tokenMap.put("-", "OPA");
        tokenMap.put("*", "OPA");
        tokenMap.put("/", "OPA");
        tokenMap.put("%", "OPA");
        tokenMap.put("++", "INC");
        tokenMap.put("--", "DEC");
        tokenMap.put(",", "CO");
        tokenMap.put("(", "PA");
        tokenMap.put(")", "PC");
        tokenMap.put("{", "LLA");
        tokenMap.put("}", "LLC");
        tokenMap.put("[", "COA");
        tokenMap.put("]", "COC");
        tokenMap.put(";", "FI");
        tokenMap.put(".", "CA");
        tokenMap.put(":", "DOP");
        tokenMap.put("?", "OPT");

        // Operadores lógicos
        tokenMap.put("==", "OPR");
        tokenMap.put("<", "OPR");
        tokenMap.put(">", "OPR");
        tokenMap.put("&&", "OPL");
        tokenMap.put("||", "OPL");
        tokenMap.put("!", "OPL");
        
        // Excepciones ilegales
        tokenMap.put("System", "ID");
        tokenMap.put("out", "ID");
        tokenMap.put("println", "ID");
    }

    public static String getToken(String lexema) {
        return tokenMap.get(lexema);
    }
}