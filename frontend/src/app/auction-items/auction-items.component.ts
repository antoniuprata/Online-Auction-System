import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { first, map } from 'rxjs';
import { AuthenticationService } from '../authentication.service';
import { AuctionItem } from '../model';

@Component({
  selector: 'auction-items',
  templateUrl: './auction-items.component.html',
  styleUrls: ['./auction-items.component.scss']
})
export class AuctionItemsComponent implements OnInit {
  auctionItems: AuctionItem[];
  currentUser: any;

  constructor(private http: HttpClient, private router: Router, private authentificationService: AuthenticationService) { }

  ngOnInit(): void {
    this.currentUser = this.authentificationService.currentUserValue;
    this.getAuctionItems();
  }

  getAuctionItems() {
    this.http.get<AuctionItem[]>('http://localhost:8080/product')
      .subscribe((data) => {
        this.auctionItems = data;
      });
  }

  viewListing(id: number) {
    this.router.navigate(['/view-listing', id]);
  }

  postToWatchlist(id: number) {
    return this.http.post<any>(`http://localhost:8080/watchlist`, { emailUser: this.currentUser.email, id: id }, { responseType: 'json' })
    .pipe(map(user => {
      //this.authentificationService.reloadPage();
      return user;
    }))
  }

  addToWatchlist(id: number) {
    this.http.post<any>(`http://localhost:8080/watchlist`, { emailUser: this.currentUser.email, idProduct: id }, { responseType: 'json' }).subscribe(data => {
        console.log(data);
    })
    /* this.postToWatchlist(id)
    .pipe(first())
    .subscribe(
      (data) => {
      },
      (error) => {
      }
    ); */
  }

}
