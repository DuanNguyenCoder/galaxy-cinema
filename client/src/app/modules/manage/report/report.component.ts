import { Component, OnInit } from '@angular/core';

interface TopMovie {
  id: number;
  name: string;
  poster: string;
  tickets: number;
  revenue: number;
}

interface DailyReport {
  date: Date;
  ticketRevenue: number;
  foodRevenue: number;
  totalRevenue: number;
  tickets: number;
}

@Component({
  selector: 'app-report-manage',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss'],
})
export class ReportComponent implements OnInit {
  timeRange: string = 'month';
  totalRevenue: number = 0;
  totalTickets: number = 0;
  foodRevenue: number = 0;
  newCustomers: number = 0;
  topMovies: TopMovie[] = [];
  dailyReports: DailyReport[] = [];

  constructor() {}

  ngOnInit(): void {
    this.loadReportData();
  }

  loadReportData(): void {
    // TODO: Implement API calls to get report data
    // For now, using mock data
    this.totalRevenue = 150000000;
    this.totalTickets = 1500;
    this.foodRevenue = 25000000;
    this.newCustomers = 150;

    this.topMovies = [
      {
        id: 1,
        name: 'Avengers: Endgame',
        poster: 'assets/images/movies/avengers.jpg',
        tickets: 500,
        revenue: 50000000,
      },
      {
        id: 2,
        name: 'Spider-Man: No Way Home',
        poster: 'assets/images/movies/spiderman.jpg',
        tickets: 450,
        revenue: 45000000,
      },
    ];

    this.dailyReports = [
      {
        date: new Date(),
        ticketRevenue: 5000000,
        foodRevenue: 1000000,
        totalRevenue: 6000000,
        tickets: 50,
      },
      {
        date: new Date(Date.now() - 86400000),
        ticketRevenue: 4500000,
        foodRevenue: 900000,
        totalRevenue: 5400000,
        tickets: 45,
      },
    ];
  }

  exportReport(): void {
    // TODO: Implement report export functionality
    console.log('Exporting report for time range:', this.timeRange);
  }
}
