<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Usuario extends Migration
{
    public function up()
    {
        Schema::create('usuario', function (Blueprint $table) {
            $table->id();
            $table->string('nombreyapellido');
            $table->string('nombreusuario');
            $table->string('contraseña');
            $table->string('email');
            $table->string('fechanac');
            $table->timestamps();
        });
    }
    public function down()
    {
        Schema::dropIfExists('usuario');
    }
}
