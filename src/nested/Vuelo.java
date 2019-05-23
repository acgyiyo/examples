package nested;

public class Vuelo {
    private int pasajeros;
    private int tripulación;

    public ProgramaDeRecompensas getPdr() {
        return pdr;
    }

    public void setPdr(ProgramaDeRecompensas pdr) {
        this.pdr = pdr;
    }

    private ProgramaDeRecompensas pdr= new ProgramaDeRecompensas();

    public static class ProgramaDeRecompensas{
        private int nivel;
        private int duracion;

        public int getNivel() {
            return nivel;
        }

        public void setNivel(int nivel) {
            this.nivel = nivel;
        }

        public int getDuracion() {
            return duracion;
        }

        public void setDuracion(int duracion) {
            this.duracion = duracion;
        }
    }


}
