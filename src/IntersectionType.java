import java.io.*;

public class IntersectionType {

    public static void main(String[] args) {

    }

    //con este método puedo cargar datos de un archivo a la clase persona
    public Person read(final DataInputStream source) {
        try (DataInputStream input = source) {
            return new Person(input.readInt(), input.readUTF());
        } catch (IOException e) {
            System.err.println("error al leer archivo-->" + e);
        }
        return null;
    }

    //con este método podemos leer archivo
    public void readInputStream() throws IOException {
        IntersectionType it = new IntersectionType();

        DataInputStream stream = new DataInputStream(
                new FileInputStream(System.getProperty("user.dir") + "\\resources\\file1.txt"));
        Person person = it.read(stream);

        System.out.println(person);

        /*el problema está cuando queremos leer el archivo con otro tipo de lecto como
        por ejemplo un randomAccess
        */
        RandomAccessFile raf = new RandomAccessFile(
                System.getProperty("user.dir") + "\\resources\\file1.txt", "rw");
        //aca se está generando el error de compilación ya que no puede usar un RandomAccess
        person = it.read(raf);
        System.out.println(person);

        /*para solucionar lo anterior podemos crear simplemente 2 metodo 1 para el inputStream y otro
            para el randomAcess pero eso sería meter código repetido
         */
    }

    /*para la solución se puede usar una interface llamada DataInput, pero dicha interface no tiene
    el metodo de closeable, entonces se puede usar un generic interceptando la capacidad de la
    interface dataInput y la de closeable así..
     */
    public <T extends DataInput & Closeable>Person read2(final T source){
        try (T input = source) {
            return new Person(input.readInt(), input.readUTF());
        } catch (IOException e) {
            System.err.println("error al leer archivo-->" + e);
        }
        return null;
    }

    //teniedo dicha solución generica podemos usar cualquiera de los 2 readers y usarlos con el mismo método
    public void readGnericInput()throws IOException{
        IntersectionType it = new IntersectionType();

        DataInputStream stream = new DataInputStream(
                new FileInputStream(System.getProperty("user.dir") + "\\resources\\file1.txt"));
        Person person = it.read2(stream);
        System.out.println(person);

        RandomAccessFile raf = new RandomAccessFile(
                System.getProperty("user.dir") + "\\resources\\file1.txt", "rw");
        person = it.read2(raf);
        System.out.println(person);

    }


}
