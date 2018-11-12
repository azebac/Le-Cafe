import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../../models/usuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Usuario[]>(`${environment.apiUrl}/users`);
  }

  getById(id: number) {
    return this.http.get(`${environment.apiUrl}/users/${id}`);
  }

  /**
   * Metodo para registrar el usuario en el sistema
   * @param user Usuario a ser registrado
   */
  registrar(user: Usuario) {
    return this.http.post(`${environment.apiUrl}/usuario/registro`, user);
  }

  actualizar(user: Usuario) {
    return this.http.put(`${environment.apiUrl}/users/${user.id}`, user);
  }

}
