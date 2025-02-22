import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DateService {
  getCurrentDate(): string {
    const now = new Date();
    const formattedDate = now.toISOString().slice(0, 16);

    return formattedDate;
  }

  getDayOfWeek(day: Date): string {
    const daysOfWeek = [
      'Chủ nhật',
      'Thứ 2',
      'Thứ 3',
      'Thứ 4',
      'Thứ 5',
      'Thứ 6',
      'Thứ 7',
    ];
    return daysOfWeek[day.getDay()];
  }

  groupShowtimesByDate(showtimes: any) {
    const grouped: any = [];

    showtimes.flat().forEach((show: any) => {
      const date = show.date;

      const time = show.startShow;

      let existingDate = grouped.find((item: any) => item.date === date);

      if (!existingDate) {
        existingDate = { date, shows: [] };
        grouped.push(existingDate);
      }

      existingDate.shows.push({
        id: show.id,
        time: time, // Chỉ lấy HH:mm
        date,
        screen: show.screen,
      });
    });

    return grouped;
  }
}
