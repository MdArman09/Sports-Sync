import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSignup() {
    this.authService.signUp(this.email, this.password).subscribe({
      next: () => {
        alert('Signup successful! Please login.');
        this.router.navigate(['/login']); 
      },
      error: (err) => {
        this.errorMessage = 'Signup failed! Try again.';
        console.error(err);
      }
    });
  }

  goToLogin() {
    this.router.navigate(['/login']); 
  }

  goHome() {
    this.router.navigate(['/landing']); 
  }
}
