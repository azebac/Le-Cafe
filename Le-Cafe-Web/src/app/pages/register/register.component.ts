import { Component, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Usuario } from 'src/app/models/usuario';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';
import { first } from 'rxjs/operators';
declare var $ :any;
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit, AfterViewInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  usuario: Usuario = new Usuario();

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private usuarioService: UsuarioService,
    private router: Router,
    private elementRef: ElementRef) { }

  ngOnInit() {
    $('#data_1').datepicker({
      todayBtn: "linked",
      keyboardNavigation: false,
      forceParse: false,
      calendarWeeks: true,
      autoclose: true
  });
    //Instancio el formulario de registro
    this.registerForm = this.formBuilder.group({
      correo: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      password: ['', Validators.required]
    });

  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#f3f3f4';

  }

  /**
  * Getter del formulario para poder acceder facil a sus controles para ver los errores
  */
  get formulario() { return this.registerForm.controls; }

  /**
  * Metodo que se dispara cuando el usuario confirma el login
  */
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.usuario.nombre = this.formulario.nombre.value;
    this.usuario.apellido = this.formulario.apellido.value;
    this.usuario.correo = this.formulario.correo.value;
    this.usuario.password = this.formulario.password.value;
    this.usuario.fechaNacimiento = new Date(this.formulario.fechaNacimiento.value.year, this.formulario.fechaNacimiento.value.month,this.formulario.fechaNacimiento.value.day);
    this.loading = true;
    this.usuarioService.registrar(this.usuario)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(["/login"]);
        },
        error => {
          //this.alertService.error(error);
          this.loading = false;
        });
  }

}
