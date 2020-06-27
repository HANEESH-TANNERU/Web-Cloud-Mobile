import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from  './../../environments/environment';

@Component({
  selector: 'app-search-recipe',

  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipe_value: any;
  place_value: any;
  venueList = [];
  recipeList = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  private EDAMAMAPPI= environment.EDAMAMAPPID;
  private EDADAMAPPKE= environment.EDADAMAPPKEY;
  private FoursquareAPICLIENTI = environment.FoursquareAPICLIENTID;
  private FoursquareAPICLIENTKE = environment.FoursquareAPICLIENTKEY;
  private recipe_url = `https://api.edamam.com/search?app_id=${this.EDAMAMAPPI}&app_key=${this.EDADAMAPPKE}`;
  private venue_url = `https://api.foursquare.com/v2/venues/search?client_id=${this.FoursquareAPICLIENTI}&client_secret=${this.FoursquareAPICLIENTKE}&v=20180323`;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipe_value = this.recipes.nativeElement.value;
    this.place_value = this.places.nativeElement.value;

    if (this.recipe_value !== null) {
      /**
       * Write code to get recipe
       */
      let food_API= this.recipe_url + '&q='+ this.recipe_value;
      this.recipeList = []
      this._http.get(food_API).subscribe( headerResponse=> {
        const data = headerResponse["hits"];
        data.map(it => {
          const obj = {
            name : it.recipe.label,
            url:it.recipe.url,
            icon: it.recipe.image
          }
          this.recipeList.push(obj);
        })
      })

    }

    if (this.place_value != null && this.place_value !== '' && this.recipe_value != null && this.recipe_value !== '') {
      /**
       * Write code to get place
       */

      let venueAPI= this.venue_url + '&query='+ this.recipe_value + '&near=' + this.place_value;
      this.venueList = [];
      this._http.get(venueAPI).subscribe( headresponse=> {
        const venues = headresponse['response']['venues'].slice(0,10);
        venues.map(event => {
          const venueObj = {
            name: event.name,
            location : {
              formattedAddress: [event.location.address,event.location.city,event.location.country]
            }
          }
          this.venueList.push(venueObj);
        })
      })
    }
  }
}
