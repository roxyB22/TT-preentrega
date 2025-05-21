import java.util.Scanner;
import java.util.ArrayList;

class Articulo {
    int id;
    String nombre;
    double precio;
    String descripcion;


    public Articulo(int id, String nombre, double precio) { //este es el constructor del objeto Articulo
        this.id = id; //el id de este artículo es el que le pasé por parámetro
        this.nombre = nombre; //es el nombre del artículo que le pasé por parámetro
        this.precio = precio; //es el precio del artículo que le pasé por parámetro
    }

    public void mostrar() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Precio: $" + precio);
    }
}


public class MenuArticulos {
    static ArrayList<Articulo> lista = new ArrayList<>(); //crea una lista dinámica vacía para los artículos

    static Scanner teclado = new Scanner(System.in); //Crea un lector de teclado llamado teclado para ingresar datos por consola
    public static void main(String[] args) {// es un array de Strings
        int opcion = 0; //inicializo la variable entera "opcion" en 0

        while (opcion != 6) {//mientras opcion sea diferente de 6 se ejecula el bucle
            System.out.println("\n----- Menú de Artículos -----");
            System.out.println("1. Crear un artículo nuevo");
            System.out.println("2. Consultar un artículo");
            System.out.println("3. Listar artículos");
            System.out.println("4. Modificar un artículo");
            System.out.println("5. Borrar un artículo");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");

            opcion = teclado.nextInt();//la opción sólo debe ser un entero. En otro caso debe lanzar una excepción

            switch (opcion) {//compara la opción ingresada por el usuario y elige qué hacer
                case 1: //si la opción es 1, realiza el siguiente bloque 
                    crearArticulo();
                    break;
                case 2: //si la opción es 2, realiza el siguiente bloque
                    consultarArticulo();
                    break;
                case 3: //si la opción es 3, realiza el siguiente bloque
                    listarArticulos();
                    break;
                case 4: //si la opción es 4, realiza el siguiente bloque
                    modificarArticulo();
                    break;
                case 5: //si la opción es 5, realiza el siguiente bloque
                    borrarArticulo();
                    break;
                case 6: //si la opción es 6, realiza el siguiente bloque
                    System.out.println("Programa finalizado.");
                    break;
                default://si la opción no es ninguna de las anteriores, realiza el siguiente bloque
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        teclado.close();// cierra el scanner teclado
    }

//METODO CREAR ARTICULO----

     public static void crearArticulo() {
        System.out.print("Ingrese el Id: ");//pido al usuario el id del artículo
        

        //validación: que el dato ingresado sea entero
        if (!teclado.hasNextInt()) { //si el dato ingresado por teclado NO es un entero
            System.out.println("❌ Debe ingresar un número entero.");//imprime el mensaje de error
            teclado.next(); // limpia lo mal escrito
            return;//como No es un entero, salir del método
        }
        //Validación: que el id no esté vacío
        
        int id = teclado.nextInt();//guarda el dato(entero) ingresado por teclado en la variable id
        teclado.nextLine();//limpia la línea de entrada

        //validación: que el id sea positivo
        if(id < 0) { //si el id es menor a 0
            System.out.println("❌ El ID debe ser un número entero positivo."); //avisar error
            return; //salir del método
        }
        
        //validación: que el id no esté repetido
        for (int i = 0; i < lista.size(); i++) { //bucle for ech que recorre la lista de artículos
             Articulo art = lista.get(i); //obtengo el objeto art(de tipo Artículo que fue definido al principio) en la posición i de la lista
            if (art.id == id) { //si el id del articulo art = al id que dio el usuario(TRUE)
                System.out.println("❌ Ya existe un artículo con ese ID."); //avisar error
                return; //salir del método
            }
        }

        System.out.print("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        
        //Validacón: que el nombre no esté vacío
        if (nombre.trim().isEmpty()) {//si el nombre está vacío o sólo tiene espacios (TRUE)
            System.out.println("❌ El nombre no puede estar vacío."); //avisa el error
            return; //salir del metodo
        }

        //Validación:que el nombre no esté repetido
        for (int i = 0; i < lista.size(); i++) {//recorre la lista de artículos
             Articulo art = lista.get(i); // obtengo el objeto art(de tipo Artículo que fue definido al principio) en la posición i de la lista
        
            if (art.nombre.equalsIgnoreCase(nombre)) { //si el nombre del artículo art = al nombre que dio el usuario (TRUE)
                System.out.println("❌ Ya existe un artículo con ese nombre.");//avisar error
            return; //salir del metodo
            }
        }

        System.out.print("Ingrese el precio: ");//le pido al usuario el precio del artículo
        
        //Validación: que el precio sea un número válido
            if (!teclado.hasNextDouble()) {//si el dato ingresado por teclado NO es un número decimal
            System.out.println("❌ Debe ingresar un número válido del tipo 12,50"); //avisa el error
            teclado.next(); // limpia lo mal escrito
            return; //salir del método
            }
        double precio = teclado.nextDouble();//guarda el dato(decimal) ingresado por teclado en la variable precio
        teclado.nextLine(); //limpia la línea de entrada
        //Validación: que el precio no sea negativo
            if (precio < 0) {//si precio es menor que 0 (TRUE)
            System.out.println("❌ El precio no puede ser negativo.");//avisar error
            return; //salir del método
            }
        
        //Agregar el artículo al Arraylist lista
        Articulo nuevo = new Articulo(id, nombre, precio);//crea un nuevo objeto Articulo con los datos ingresados por el usuario
        lista.add(nuevo); //agrega el nuevo objeto a la lista de artículos
        System.out.println("✅ Artículo agregado correctamente."); //avisa que se agregó correctamente
    }

//MÉTODO CONSULTAR ARTICULO------------

    public static void consultarArticulo() {
        System.out.print("Ingrese el NOMBRE del artículo: ");
        String nombre = teclado.nextLine();//guarda en la variable nombre el dato ingresado por teclado


    // Validación: que nombre no esté vacío o solo con espacios
    if (nombre.trim().isEmpty()) {//metodos encadenados para que el nombre no esté vacío o sólo contenga espacios
        //nombre.trim da un string y isEmpty devuelve un booleano
        System.out.println("❌ El nombre no puede estar vacío.");//si es TRUE, avisa el error
        return;//sale del metodo
    }

    // Buscar coincidencia en la lista
    for (int i = 0; i < lista.size(); i++) {//recorre la lista de articulos
        Articulo art = lista.get(i); //obtengo el objeto art(de tipo Artículo que fue definido al principio) en la posición i de la lista
        if (art.nombre.equalsIgnoreCase(nombre)) {//al nombre del articulo art le aplico el metodo para ver si es igual al nombre obtenido por teclado
            //devuelve un booleano
            art.mostrar(); //si es TRUE, muestra el artículo
            return; //sale del metodo
        }
    }

    System.out.println("❌ Artículo no encontrado."); //si es FALSE, avisa que no lo encontró
}


        
// MÉTODO LISTAR ARTICULOS----------------
    public static void listarArticulos() {
        if (lista.isEmpty()) {//si el ArrayList lista está vacío (TRUE)
            System.out.println("📭 No hay artículos registrados.");//enviar mensaje
            return; //salir del método
        } 
            System.out.println("📦 Lista de artículos:");//enviar mensaje
            for (int i = 0; i < lista.size(); i++) {//recorre la lista de artículos
                Articulo art = lista.get(i); //obtengo el objeto art(de tipo Artículo, que fue definido al principio) en la posición i de la lista
                art.mostrar();//método mostrar definido al principio: muestra en cada recorrido los atributos del artículo en la posicion i
            }
        }
    
// MÉTODO MODIFICAR ARTICULO----------------
    public static void modificarArticulo() {
        System.out.print("Ingrese el nombre del artículo que desea modificar: ");//pido al usuario que ingrese el nombre por teclado
        String nombre = teclado.nextLine(); //guardo en la variable nombre el dato ingresado por teclado
        
    // Validación: que nombre no esté vacío o solo con espacios
        if (nombre.trim().isEmpty()) {//metodos encadenados para que el nombre no esté vacío o sólo contenga espacios
            //nombre.trim da un string y isEmpty devuelve un booleano
            System.out.println("❌ El nombre no puede estar vacío.");//si es TRUE, avisa el error
            return;//sale del metodo
        }

        for (int i = 0; i < lista.size(); i++) {//recorre la lista de artículos
            Articulo art = lista.get(i);// guarda en art, el objeto en la posi
            if (art.nombre.equalsIgnoreCase(nombre)) {//si el 
                //o sea si es TRUE
                System.out.println("📝 Artículo encontrado:");//mostrar mensaje
                art.mostrar();//mostrar los atributos del objeto
        
                // luego mostrar el Menú de modificación
                System.out.println("Ingrese la opción que desea modificar: ");
                System.out.println("1. ID");
                System.out.println("2. Nombre");
                System.out.println("3. Precio");
                int opcion = teclado.nextInt(); //guardo en la variable opcion el dato ingresado por teclado
                teclado.nextLine(); // limpia el ENTER pendiente
   
                switch (opcion) { //compara la opción ingresada por el usuario y elige qué hacer
                    
                    case 1: // ----si opción es 1 realiza el siguiente bloque
                        System.out.print("Ingrese el nuevo ID: ");// pide al usuario el nuevo ID
                    // Validación: que el dato ingresado sea entero
                        if (!teclado.hasNextInt()) { 
                            System.out.println("❌ Debe ingresar un número entero.");
                            teclado.next(); // 
                            return;
                        }
                    int nuevoId = teclado.nextInt();// guarda el nuevo ID ingresado por teclado
                    teclado.nextLine(); //limpia el ENTER pendiente
                    
                    // Validación: que el nuevo ID sea positivo
                        if (nuevoId < 0) {
                            System.out.println("❌ El ID no puede ser negativo.");
                            return;
                        }

                    // Validación: que el nuevo ID no esté repetido (en otro artículo)
                        for (int j = 0; j < lista.size(); j++) {//recorre la lista de artículos con otro índice(j)
                            //porque estoy dentro del for de la linea 195 que ya tiene un i
                            if (j != i && lista.get(j).id == nuevoId) {
                                System.out.println("❌ Ya existe un artículo con ese ID.");
                                return;
                            }
                        }
                    case 2: //---si opcion es 2, realiza el siguiente bloque
                        System.out.print("Ingrese el nuevo nombre: "); //pide al usuario el nuevo nombre
                        String nuevoNombre = teclado.nextLine(); //guarda en la variable nuevoNombre el dato ingresado por teclado

                        if (nuevoNombre.trim().isEmpty()) {
                            System.out.println("❌ El nombre no puede estar vacío.");
                            return;
                        }

                    // Validación: que el nuevo nombre no esté repetido
                        for (int j = 0; j < lista.size(); j++) {//recorre la lista de artículos con otro índice(j)
                            //porque estoy dentro del for de la linea 195 que ya tiene un i
                            if (j != i && lista.get(j).nombre.equalsIgnoreCase(nuevoNombre)) {//para cada articulo j de la lista verifico que el nombre no exista
                                //condicional: que j sea diferente de i
                                //que el nuevoNombre sea igual al nombre del articulo j
                                //si es TRUE quiere decir que ya existe un articulo con ese nombre
                                System.out.println("❌ Ya existe un artículo con ese nombre.");//avise al usuario
                                return;//salga del método
                            }
                        }//si es FALSE, quiere decir que NO existe un artículo con ese nombre, entonces continúe el código

                    art.nombre = nuevoNombre; //guarde en la variable nombre del objeto art, el nuevo nombre
                    System.out.println("✅ Nombre modificado correctamente."); //muestre mensaje de éxito
                    break;//sale del switch y vuelva al mini menu

                    case 3: //---si opcion es 3, realiza el siguiente bloque
                        System.out.print("Ingrese el nuevo precio: ");//pide al usuario el nuevo precio
                              
                    // Validación: que sea un número decimal válido
                        if (!teclado.hasNextDouble()) {//si el valor ingresado NO es un decimal
                            System.out.println("❌ El número ingresado no es válido. Por favor recuerde usar el . en vez de la ,");//si es TRUE, avisa el error
                            teclado.next(); // limpia el valor incorrecto
                            return; //salir del método
                        }

                    double nuevoPrecio = teclado.nextDouble();//guarde en la variable nuevoPrecio el dato ingresado por teclado
                    teclado.nextLine(); // limpia el ENTER pendiente

                    //Validación: que no sea negativo
                        if (nuevoPrecio < 0) {//si el nuevo precio es negativo(TRUE)
                            System.out.println("❌ El precio no puede ser negativo."); //avise el error
                            return; //salir del método
                        }
                    
                    lista.get(i).precio = nuevoPrecio;//actualice el precio del artículo en la lista
                    System.out.println("✅ Precio modificado correctamente."); //envíe mensaje de éxito
                    break;// sale del switch y vuelva al mini menu
                    
                    default: //si la opción no es ninguna de las anteriores, realiza el siguiente bloque
                        System.out.println("❌ Opción inválida. Intente nuevamente.");
                        return; //sale del método
                }
        }
        System.out.println("❌ Artículo no encontrado.");
    }
    }

// MÉTODO BORRAR ARTICULO----------------    
    public static void borrarArticulo() {
        System.out.print("Ingrese el NOMBRE del artículo a borrar: "); //pido al usuario el nombre del artículo a borrar
        String nombre = teclado.nextLine();//guardo en la variable nombre el dato ingresado por teclado

    // Validación: que no esté vacío o solo espacios
    if (nombre.trim().isEmpty()) {//metodos encadenados para que el nombre no esté vacío o sólo contenga espacios
        //nombre.trim da un string y isEmpty devuelve un booleano
        System.out.println("❌ El nombre no puede estar vacío.");//si es TRUE, avisa el error
        return; //sale del metodo
    }

    // Buscar y eliminar
    for (int i = 0; i < lista.size(); i++) {//recorre la lista de artículos
        Articulo art = lista.get(i); // guarda en art el objeto en la posición i de la lista

        //compara el nombre del artículo art con el nombre ingresado por teclado
        if (art.nombre.equalsIgnoreCase(nombre)) { //si son iguales (TRUE)
            lista.remove(i); // Elimina el artículo de la lista
            System.out.println("🗑️ Artículo eliminado correctamente.");//envía mensaje de éxito
            return; // Sale del método
        }
    }

    System.out.println("❌ Artículo no encontrado."); //si es FALSE, avisa que no lo encontró
}
}