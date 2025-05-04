import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Router } from '@angular/router';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-landing',
  standalone: true, // <-- Make sure this is set
  imports: [CommonModule, HttpClientModule, RouterModule], // <-- ✅ Add HttpClientModule here
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  newsList: any[] = [];

  constructor(private newsService: NewsService, private router: Router) {}

  ngOnInit(): void {
    this.newsService.getNews().subscribe({
      next: (response) => {
        this.newsList = response.articles;
      },
      error: (err) => {
        console.error('Error fetching news:', err);
      }
    });
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  signup(): void {
    this.router.navigate(['/signup']);
  }
}
