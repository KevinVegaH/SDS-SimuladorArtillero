/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallersimulacion;

/**
 *
 * @author kevin_000
 */
public class TallerSimulacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int i = 0;
        double ganancia = 49; // 37,4
        double operacion;

        //-----  Proceso anterior -----//
        int[] a = {0, 0, 1, 4, 4, 5, 7, 9}; // Tiempos de llegada //
        int[] s = {0, 2, 1, 2, 1, 1, 3, 2};// Tiempos de servicio //
        
        //int[] a = {0, 2, 3, 3, 5, 6, 8, 10};
        //int[] s = {0, 1, 2, 3, 1, 2, 1, 2};
        
        //int[] a = {0, 0, 1, 2, 3, 4, 7, 8, 10};
        //int[] s = {0, 1, 2, 3, 1, 2, 1, 1, 1};
   
        //-----  Proceso nuevo -----//
        
         //int[] a = {0, 0, 1, 1, 3, 3, 4, 6, 8, 9};
         //int[] s = {0, 1, 2, 1, 1, 3, 1, 1, 1, 1};
        
         //int[] a = {0,0, 0, 3, 3, 5, 5, 7, 7, 8, 9};
         //int[] s = {0,1, 1, 1, 1, 1, 1, 1, 1, 2, 1};
         
         //int[] a = {0, 1, 1, 1, 3, 3, 5, 6, 7, 9};
         //int[] s = {0, 2, 1, 1, 1, 1, 1, 2, 1, 2};
         
         
        int[] c = new int[a.length]; // Almacena los tiempos de partida //
        int[] d = new int[a.length]; // Almacena los tiempos de espera //
        int[] b = new int[a.length];// Almacena los tiempo en que comienza el servicio //
        double[] g = new double[a.length];//Almacena las ganancias //

        while (i < a.length - 1) {
            i++;

            if (a[i] < c[i - 1]) {

                d[i] = c[i - 1] - a[i]; //<-- Calcula el tiempo de espera.

            } else {

                d[i] = 0;
            }

            c[i] = a[i] + d[i] + s[i]; //<-- Calcula el tiempo de partida.

        }
        System.out.println("-----TIEMPO DE PARTIDA-----");
        for (int n = 1; n <= a.length - 1; n++) {

            System.out.println("Lancha " + (n) + " partió a las: " + c[n]);

        }

        System.out.println("-----TIEMPO DE ESPERA------");

        for (int n = 1; n <= a.length - 1; n++) {

            System.out.println("Lancha " + (n) + " esperó: " + d[n]);

            if (d[n] > 2) {

                g[n] = ganancia - 5;

            } else if (c[n] > 12) {

                operacion = (c[n] - 12) * 10;
                g[n] = ganancia - operacion;

            } else if (d[n] > 2 && c[n] > 12) {

                operacion = (c[n] - 12) * 10;
                g[n] = ganancia - operacion - 5;

            } else {

                g[n] = ganancia;

            }

        }
        System.out.println("----T. COMIENZA SERVICIO----");

        for (int j = 0; j < a.length; j++) {

            b[j] = a[j] + d[j];

        }
        for (int j = 1; j <= a.length - 1; j++) {

            System.out.println("Lancha " + j + ": " + b[j]);
        }
        

        System.out.println("----GANANCIAS POR LANCHA----");

        for (int j = 1; j <= a.length - 1; j++) {

            System.out.println("Lancha " + j + ": " + g[j]+"$");

        }

        double dPlus = 0.0;
        double sPlus = 0.0;
        double aPlus = 0.0;
        double gPlus = 0.0;

        for (int j = 0; j < a.length; j++) {

            dPlus += d[j]; //<-- Suma los datos de d y almacena el resultado.
            sPlus += s[j]; //<-- Suma los datos de s y almacena el resultado.
            gPlus += g[j]; //<-- Suma los datos de s y almacena el resultado.
        }
        System.out.println("------GANANCIA TOTAL---------");
        System.out.println("Ganancia Total: " + gPlus+"$");
        System.out.println("-----------------------------");
        for (int y = 1; y < a.length; y++) {

            aPlus += a[y] - a[y - 1]; //<-- Calcula el intervalo de trabajo y los suma.

        }

        double n = i;

        double promedio_tiemp_entre_llegada = aPlus / n;

        double promedio_retraso = dPlus / n; // d-barra.

        double promedio_tiempo_ser = sPlus / n; // S-barra.

        double tasa_llegada = 1 / promedio_tiemp_entre_llegada;

        double tasa_servicio = 1 / promedio_tiempo_ser;

        double w = promedio_retraso + promedio_tiempo_ser; // W-barra.

        double I, Q, X, an, sn, dn, cn;

        an = a[a.length - 1];//<-- El ultimo elemento de a.
        sn = s[s.length - 1];//<-- El ultimo elemento de s.
        dn = d[d.length - 1];//<-- El ultimo elemento de d.

        cn = an + sn + dn; //<-- Es el ultimo tiempo.

        I = (n / cn) * w;
        Q = (n / cn) * promedio_retraso;
        X = (n / cn) * promedio_tiempo_ser;

        System.out.println("-----------------------------");
        System.out.println("Tiempo promedio entre llegadas: " + promedio_tiemp_entre_llegada + " segundos por trabajo.");
        System.out.println("Promedio Tiempo de Servicio: " + promedio_tiempo_ser + " segundos por trabajo.");
        System.out.println("Tasa de llegada: " + String.format("%.3f", (tasa_llegada)) + " trabajos por segundo.");
        System.out.println("Tasa de servicio: " + String.format("%.3f", (tasa_servicio)) + " trabajos por segundo.");
        System.out.println("Promedio de Retraso: " + promedio_retraso);
        System.out.println("Promedio de espera: " + w);
        System.out.println("-----------------------------");

        System.out.println("número promedio de tiempo en el nodo(I): " + String.format("%.3f", (I)));
        System.out.println("número promedio de tiempo en la cola(Q): " + String.format("%.3f", (Q)));
        System.out.println("Número promediado en el tiempo de servicio(X): " + String.format("%.3f", (X)));

    }

}
