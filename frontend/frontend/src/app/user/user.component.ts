import { Component, OnInit } from '@angular/core';
import { Client, over } from 'stompjs';
import SockJS from 'sockjs-client';
import { CommonModule } from '@angular/common';
import { EventItemComponent } from '../components/single-items/event-item/event-item.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, EventItemComponent, HttpClientModule],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: [NewsService]
})
export class UserComponent implements OnInit {

  events: any[] = [];
  newsList: any[] = [];
  private stompClient!: Client;

  constructor(private http: HttpClient, private newsService: NewsService) {}

  ngOnInit(): void {
    this.fetchExistingEvents();
    this.connectWebSocket();
    this.getTrendingNews();
  }

  fetchExistingEvents() {
    this.http.get<any[]>('http://localhost:8080/api/match').subscribe({
      next: (data) => {
        this.events = data.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      },
      error: (err) => {
        console.error('Failed to fetch events:', err);
      }
    });
  }

  connectWebSocket() {
    const socket = new SockJS('http://localhost:8080/ws');
    this.stompClient = over(socket);

    this.stompClient.connect({}, () => {
      this.stompClient.subscribe('/topic/events', (message) => {
        if (message.body) {
          const event = JSON.parse(message.body);
          this.events.unshift(event);
        }
      });
    });
  }

  getTrendingNews() {
    this.newsService.getNews().subscribe({
      next: (response) => {
        this.newsList = response.articles;
      },
      error: (err) => {
        console.error('Failed to fetch news:', err);
      }
    });
  }

  logout() {
    localStorage.clear();
    window.location.href = '/login';
  }
}
