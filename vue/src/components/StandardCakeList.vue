<template>
  <div>
      <h1>Order A Pre-made Cake</h1>
      <form v-on:submit.prevent="orderStandardCake()">
      <div v-for="cake in standardCakes" v-bind:key="cake.cakeId">
          <center>
          <div class="premadeCake" v-if="cake.available">
          <label for="premade">
          <img class="premade-cake-image" v-bind:src="cake.imageURL">
          <br>
         <div class="premade-cake-description"><input type="radio" name="premade" v-model="newOrder.cake_id" :value="cake.cakeId">{{cake.size}} {{cake.shape}} {{cake.numOfTiers}}-tiered {{cake.flavor}} Cake With {{cake.icing}} Icing, {{cake.filling}} Filling: ${{cake.price}}</div> 
          </label>
          </div>
          </center>
      </div>
      <h2>Customer Information</h2>
      <div>
      <label for="customerName">Name: </label>
      <input type="text" v-model="newOrder.customer_name" name="customerName" required="true" maxlength="25">
      </div>
      <div>
      <label for="customerAddress">Address: </label>
      <input type="text" v-model="newOrder.customer_address" name="customerAddress" required="true" maxlength="100">
      </div>
      <div>
      <label for="customerCity">City: </label>
      <input type="text" v-model="newOrder.customer_city" name="customerCity" required="true" maxlength="25">
      </div>
      <div>
      <label for="customerState">State: </label>
      <select class= "Select"  v-model="newOrder.customer_state" name="customerState" required="true">
        <option value="AL">Alabama</option>
        <option value="AK">Alaska</option>
        <option value="AZ">Arizona</option>
        <option value="AR">Arkansas</option>
        <option value="CA">California</option>
        <option value="CO">Colorado</option>
        <option value="CT">Connecticut</option>
        <option value="DE">Delaware</option>
        <option value="DC">District Of Columbia</option>
        <option value="FL">Florida</option>
        <option value="GA">Georgia</option>
        <option value="HI">Hawaii</option>
        <option value="ID">Idaho</option>
        <option value="IL">Illinois</option>
        <option value="IN">Indiana</option>
        <option value="IA">Iowa</option>
        <option value="KS">Kansas</option>
        <option value="KY">Kentucky</option>
        <option value="LA">Louisiana</option>
        <option value="ME">Maine</option>
        <option value="MD">Maryland</option>
        <option value="MA">Massachusetts</option>
        <option value="MI">Michigan</option>
        <option value="MN">Minnesota</option>
        <option value="MS">Mississippi</option>
        <option value="MO">Missouri</option>
        <option value="MT">Montana</option>
        <option value="NE">Nebraska</option>
        <option value="NV">Nevada</option>
        <option value="NH">New Hampshire</option>
        <option value="NJ">New Jersey</option>
        <option value="NM">New Mexico</option>
        <option value="NY">New York</option>
        <option value="NC">North Carolina</option>
        <option value="ND">North Dakota</option>
        <option value="OH">Ohio</option>
        <option value="OK">Oklahoma</option>
        <option value="OR">Oregon</option>
        <option value="PA">Pennsylvania</option>
        <option value="RI">Rhode Island</option>
        <option value="SC">South Carolina</option>
        <option value="SD">South Dakota</option>
        <option value="TN">Tennessee</option>
        <option value="TX">Texas</option>
        <option value="UT">Utah</option>
        <option value="VT">Vermont</option>
        <option value="VA">Virginia</option>
        <option value="WA">Washington</option>
        <option value="WV">West Virginia</option>
        <option value="WI">Wisconsin</option>
        <option value="WY">Wyoming</option>
      </select>				
      </div>
      <div>
      <label for="customerZip">Zip Code: </label>
      <input type="number" v-model="newOrder.customer_zip" name="customerZip" required="true" max="99999" min="11111">
      </div>
      <div>
      <label for="customerPhone">Phone Number: </label>
      <input type="text" v-model="newOrder.customer_phone" name="customerPhone" required="true" maxlength="15">
      </div>
      <div>
      <label for="customerEmail">Email: </label>
      <input type="email" v-model="newOrder.customer_email" name="customerEmail" maxlength="50">
      </div>
      <div>
      <label for="pickupDate">Pickup Date and Time: </label>
      <input type="datetime-local" v-model="newOrder.pickup_date" v-on:change="isDateAvailable()" required="true">
      </div>
      <div>
      <label for="addMessage">Add Message (+ $5.00)</label>
      <input type="checkbox" v-model="addMessage">
      <label for="message" v-if="addMessage">Message: </label>
      <input type="text" v-if="addMessage" v-model="newOrder.message" name="message" maxlength="25">
            </div>
      <div>
      <h2>Total price: ${{getCakePrice(newOrder.cake_id)}}</h2>
      <input class= "navButton" type="submit">
      </div>
      </form>
  </div>
</template>

<script>
import orderService from '../services/OrderService'
import emailService from '@emailjs/browser'
export default {
    name: "standard-cake-list",
    data() {
        return {
            standardCakes: [],
            newOrder: { cake_id: 1, price: 0.00, status: 'pending' },
            addMessage: false,
            showErrorMessage: [],
            validTime: false,
        }
    },
    computed: {
        tomorrowDate: function() {
            const fullDate = new Date();
            const year = fullDate.getFullYear();
            const month = (fullDate.getMonth() + 1).toString().padStart(2, "0");
            const today = fullDate.getDate() + 1;
            const day = today.toString().padStart(2, "0") + 1;
            const date = year + '-' + month + '-' + day;
            return date;
            }
    },
    created() {
            orderService.getStandardCakes().then(
                response => {
                    console.log(response.data);
                    this.standardCakes = response.data;
                }
            );
        },
        methods: {
            orderStandardCake() {
            if(!this.addMessage) {
                this.newOrder.message = '';
            }
            if(!this.isSelectedCakeAvailable(this.newOrder.cake_id)) {
                alert("You must select an available cake!");
                return;
            }
            if(!this.validTime) {
                alert("You must select a pickup time when the bakery is open!");
                return;
            }
            this.newOrder.price = this.getCakePrice(this.newOrder.cake_id);
            orderService.orderStandardCake(this.newOrder).then((response) => {
                    console.log(response);
                    let newOrderId = response.data;
                    this.sendEmail(newOrderId);
                    this.$router.push({
                        name: 'orderconfirmed',
                        params: {
                            confirmationNumber: newOrderId
                        }
                        });
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },
            getCakePrice(id) {
                let index = this.standardCakes.findIndex((item) => {
                    return item.cakeId == id;
                });
                if(this.addMessage) {
                    return this.standardCakes[index].price + 5;
                }
                else {
                    return this.standardCakes[index].price
                }
            },
            isSelectedCakeAvailable(id) {
                let index = this.standardCakes.findIndex((item) => {
                    return item.cakeId == id;
                });
                if(this.standardCakes[index].available) {
                    return true;
                } else {
                    return false;
                }
            },
        isDateAvailable() {
            if(this.newOrder.pickup_date < this.tomorrowDate) {
                alert("You must select a date in the future.");
                return;
            }
            orderService.isDateAvailable(this.newOrder.pickup_date).then(
                response => {
                    this.showErrorMessage = response.data;
                    this.validTime = response.data;
                })
                .catch(error => {
                    console.log(error.data);
                });
            },

             sendEmail(confirmationNumber) {
      try {
          let parameters = {
          name: this.newOrder.customer_name,
          email: this.newOrder.customer_email,
          order_number: confirmationNumber,
          pickup: this.newOrder.pickup_date
        };
        emailService.send('service_jy2nbh7', 'template_k3jc30p', parameters, 'q9qehHlikUSOhSbDC');

      } catch(error) {
          console.log({error})
      }

    },

        }
}
</script>

<style scoped>

img {
    width: 380px;
    height: 400px;
}

.premade-cake-description {
    font-size: large;
}

</style>