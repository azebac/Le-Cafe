import { Reserva } from "./reserva";
import { Recompensa } from "./recompensa";

export class Usuario{
    id: number;
    correo: string;
    nombre: string;
    apellido: string;
    fechaNacimiento: Date;
    puntos: number;
    estatus: string;
    reservaciones: Reserva[] = [];
    recompensas: Recompensa[] = [];

}