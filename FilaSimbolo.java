public class FilaSimbolo {
    private String id;
    private String lexema;
    private String token;
    private String tipoDato;
    private boolean esDeclaracion;

    public FilaSimbolo(String id, String lexema, String token, String tipoDato, boolean esDeclaracion) {
        this.id = id;
        this.lexema = lexema;
        this.token = token;
        this.tipoDato = tipoDato;
        this.esDeclaracion = esDeclaracion;
    }

    public Object[] convertirAFila() {
        return new Object[]{id, lexema, token};
    }

    public String getId() {
        return id;
    }
    public String getLexema() {
        return lexema;
    }
    public String getToken() {
        return token;
    }
    public String getTipoDato() {
        return tipoDato;
    }
    public boolean esDeclaracion() {
        return esDeclaracion;
    }
}