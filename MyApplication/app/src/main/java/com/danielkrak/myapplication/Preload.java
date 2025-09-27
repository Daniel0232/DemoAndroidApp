package com.danielkrak.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Preload extends AppCompatActivity {
    /**
     * Cuando Android Studio te crea una Activity (en este caso Preload), básicamente te genera un "esqueleto":
     *
     * onCreate(...) → es el método que se ejecuta cuando se abre esa pantalla.
     *
     * EdgeToEdge.enable(this); → hace que la pantalla aproveche todo el espacio disponible (debajo de la barra de estado y navegación).
     *
     * setContentView(R.layout.activity_preload); → carga el layout XML asociado (activity_preload.xml).
     *
     * El ViewCompat.setOnApplyWindowInsetsListener(...) → ajusta los márgenes para que los elementos no queden tapados por la barra de estado o de navegación.
     *
     * En resumen: lo que tienes es una pantalla vacía con soporte para "edge-to-edge UI", lista para que tú le pongas contenido.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    /**
     * Aquí ocurre lo siguiente:
     * Se crea una instancia de Handler que se asocia al thread principal (UI thread).
     *
     * Con el método postDelayed(...) se programa la ejecución de un bloque de código Runnable después de un retraso especificado (en este ejemplo, 5000 milisegundos,
     * es decir, 5 segundos). Qué hace el bloque Runnable:
     * Dentro de run(), se crea un Intent que inicia la MainActivity (la pantalla principal de la aplicación), y se empieza esa Activity con startActivity(...).
     *
     * Inmediatamente después, se llama a finish(), lo que cierra la Activity Preload para que no quede en la pila de Activities.
     *
     * Propósito:
     * Este Handler queda programado para que, después de 5 segundos, se realice la transición desde la pantalla de precarga (splash screen) hasta la pantalla
     * principal de la aplicación.**/

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run (){
                startActivity(new Intent(Preload.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}