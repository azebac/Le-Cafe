import { Recompensa } from "./recompensa";
import { Condicion } from "./condicion";

export class Evento {
    id: number;
    nombre: string;
    reto: boolean;
    estatus: string;
    fechaInicio: Date;
    fechaFin: Date;
    recompensas: Recompensa[] = [];
    condiciones: Condicion[] = [];
}