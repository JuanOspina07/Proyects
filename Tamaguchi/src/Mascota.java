import java.io.Serializable;

public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    private int hambre;
    private int entreno;
    private int sucio;
    private int energia;
    private int nivel;
    private boolean vivo;

    public Mascota() {
        this.hambre = 50;
        this.entreno = 50;
        this.sucio = 50;
        this.energia = 50;
        this.nivel = 1;
        this.vivo = true;
    }

    public void comer() {
        if (vivo) {
            hambre -= 10;
            sucio +=10;
            if (hambre < 0) hambre = 0;
        }
    }

    public void entreno() {
        if (vivo) {
            energia -= 10;
            entreno += 10;
            hambre +=10;
            if (energia < 0) energia = 0;
        }
    }

    public void bañar() {
        if (vivo) {
            sucio = 0;
        }
    }

    public void dormir() {
        if (vivo) {
            energia += 20;
            hambre += 10;
            sucio += 10;
            if (energia > 100) energia = 100;
        }
    }

    public void actualizarEstados() {
        if (vivo) {
            hambre += 1;
            sucio += 1;
            energia -= 1;
            entreno -=1;

            if (hambre > 100 || energia <= 0 || sucio > 100||entreno<=0) {
                vivo = false;
            }

            if (entreno >= 100 ) {
                subirNivel();
            }
        }
    }

    private void subirNivel() {
        nivel++;
        entreno = 50;
        hambre = 50;
        sucio = 50;
        energia = 50;
    }

    public boolean vida() {
        return vivo;
    }

    // Getters y setters para los atributos

    public int getHambre() {
        return hambre;
    }

    public int getEntreno() {
        return entreno;
    }

    public int getSucio() {
        return sucio;
    }

    public int getEnergia() {
        return energia;
    }

    public int getNivel() {
        return nivel;
    }
}
