export interface Breadcrumb {
  label: string;
  url: string;
  selected?: boolean;
}

export interface Seat {
  number: number;
  selected?: boolean;
  row: string;
  ordered?: boolean;
}

export interface MovieShowTime {
  id?: number;
  filmID?: number;
  ageLimited?: number;
  image?: any;
  showTimes: Showtime[];
  time?: string;
  name?: string;
}

export interface Movie {
  id?: number;
  ageLimited?: number;
  image?: any;
  showTimeID?: number;
  time?: string;
  name?: string;
  typefilm?: string;
  description?: string;
  link?: string;
  active?: number;
  screen?: string;
  actor?: string;
  author?: string;
  dateStart?: Date;
  country?: string;
}

export interface DateInfo {
  dayOfWeek: string;
  month: string;
  day: number;
}

export interface Food {
  id?: number;
  name?: string;
  price?: number;
  type?: string;
  stock?: number;
  avaiable?: number;
  createDay?: Date;
  updateDay?: Date;
  image?: string;
}

export interface Review {
  rate?: number;
  rateDate?: any;
  id?: number;
  comment?: string;
  customer?: Customer;
  film?: Movie;
}
export interface Showtime {
  id?: number;
  startShow?: string;
  screen?: string;
  date?: string;
}

export interface ticket {
  seat?: string;
  price?: number;
  type?: string;
  createdDate?: string;
}
export interface comboFood {
  name: string;
  price: number;
  quantity: number;
  image: string;
}

export interface Customer {
  id?: number;
  name?: string;
  email?: string;
  phone?: string;
  point?: number;
  passwords?: string;
  isLogin?: boolean;
}
