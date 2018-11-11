import { Usuario } from "./usuario";
import { Plato } from "./plato";

export class Cuenta{
    id: number;
    total: number;
    usuarios: Usuario[] = [];
    platos: Plato[] = [];
}