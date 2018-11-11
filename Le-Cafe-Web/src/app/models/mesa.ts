import { Reserva } from "./reserva";

export class Mesa{
    id: number;
    activa: boolean;
    reservas: Reserva[] = [];
}