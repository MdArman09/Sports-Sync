import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  private adminEmail: string = 'admin@example.com';
  private adminPassword: string = 'admin1234';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    if (this.email === this.adminEmail && this.password === this.adminPassword) {
      this.router.navigate(['/home']); 
      return;
    }

    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        this.router.navigate(['/users']); 
      },
      error: (err) => {
        this.errorMessage = 'Invalid email or password';
        console.error(err);
      }
    });
  }

  goToSignup() {
    this.router.navigate(['/signup']);
  }
  goHome() {
    this.router.navigate(['/landing']); 
  }
}
