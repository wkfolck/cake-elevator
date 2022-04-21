<template>
  <div class="customCakeOrder">
      <h2>Cake Information</h2>
      <form class="cakeOrder">
          <div>
          <label for="flavor">Flavor:</label>
          <select class= "Select" v-model="newCake.flavor" name="flavor">
              <option v-for="flavor in cakeFlavors" v-bind:key="flavor.optionId" :value="flavor.optionName">{{flavor.optionName}} (+${{flavor.additionalPrice}})</option>
          </select>
          </div>
          <div>
          <label for="icing">Icing:</label>
          <select class= "Select"  v-model="newCake.icing" name="icing">
              <option v-for="icing in cakeIcings" v-bind:key="icing.optionId" :value="icing.optionName">{{icing.optionName}} (+${{icing.additionalPrice}})</option>
          </select>
          </div>
         <div>
          <label for="filling">Filling:</label>
          <select class= "Select"  v-model="newCake.filling" name="filling">
              <option v-for="filling in cakeFillings" v-bind:key="filling.optionId" :value="filling.optionName">{{filling.optionName}} (+${{filling.additionalPrice}})</option>
              <option value="None">None</option>
          </select>
          </div>
          <div>
          <label for="size">Size:</label>
          <select class= "Select"  v-model="newCake.size" name="size">
              <option v-for="size in cakeSizes" v-bind:key="size.optionId" :value="size.optionName">{{size.optionName}} (+${{size.additionalPrice}})</option>
          </select>
          </div>
          <div>
          <label for="tiers">Tiers:</label>
          <select class= "Select"  v-model="newCake.numOfTiers" name="tiers">
              <option value=1>1 tier</option>
              <option value=2>2 tiers (+ $5.00)</option>
              <option value=3>3 tiers (+ $10.00)</option>
          </select>
          </div>
          <div>
          <label for="shape">Shape:</label>
          <select class= "Select"  v-model="newCake.shape" name="tiers">
              <option v-for="shape in cakeShapes" v-bind:key="shape.optionId" :value="shape.optionName">{{shape.optionName}} (+${{shape.additionalPrice}})</option>
          </select>
          </div>
      </form>


    <h2>Customer Information</h2>
      <form v-on:submit.prevent="submitOrder()">
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
      <h2>Total price: ${{getPrice()}}</h2>
      
      <input class="navButton" type="submit">
      </div>
      </form>
  </div>
</template>

<script>
import orderService from '../services/OrderService';
import emailService from '@emailjs/browser';
export default {
name: 'custom-cake-order',
    data() {
        return {
            newCake: {filling: 'None', numOfTiers: 1, standard: false, available: true, price: 0.00},
            newOrder: { status: 'pending' },
            addMessage: false,
            cakeFlavors: [],
            cakeIcings: [],
            cakeFillings: [],
            cakeSizes: [],
            cakeShapes: [],
            validTime: false
        }
    },
    created() {
            orderService.getCakeFlavors().then(
                response => {
                    console.log(response.data);
                    this.cakeFlavors = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeIcings().then(
                response => {
                    console.log(response.data);
                    this.cakeIcings = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeFillings().then(
                response => {
                    console.log(response.data);
                    this.cakeFillings = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeSizes().then(
                response => {
                    console.log(response.data);
                    this.cakeSizes = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeShapes().then(
                response => {
                    console.log(response.data);
                    this.cakeShapes = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
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
    methods: {
        getPrice() {
            let basePrice = 20.00 + this.getFlavorPrice(this.newCake.flavor) + this.getIcingPrice(this.newCake.icing) +
            this.getFillingPrice(this.newCake.filling) + this.getSizePrice(this.newCake.size) + this.getShapePrice(this.newCake.shape);
            if(this.newCake.numOfTiers == 2) {
                basePrice += 5.00;
            }
            if(this.newCake.numOfTiers == 3) {
                basePrice += 10.00;
            }
            if(this.addMessage) {
                basePrice += 5.00;
            }
            return basePrice;
        },
        getFlavorPrice(flavor) {
           const flavorArray = this.cakeFlavors;
           let results = 0;
           for(let i = 0; i < flavorArray.length; i++) {
               if(flavorArray[i].optionName == flavor) {
                   results = flavorArray[i].additionalPrice;
               }
           }
           return results;
        },
        getIcingPrice(icing) {
           const icingArray = this.cakeIcings;
           let results = 0;
           for(let i = 0; i < icingArray.length; i++) {
               if(icingArray[i].optionName == icing) {
                   results = icingArray[i].additionalPrice;
               }
           }
           return results;
        },
        getFillingPrice(filling) {
           const fillingArray = this.cakeFillings;
           let results = 0;
           for(let i = 0; i < fillingArray.length; i++) {
               if(fillingArray[i].optionName == filling) {
                   results = fillingArray[i].additionalPrice;
               }
           }
           return results;
        },
        getSizePrice(size) {
           const sizeArray = this.cakeSizes;
           let results = 0;
           for(let i = 0; i < sizeArray.length; i++) {
               if(sizeArray[i].optionName == size) {
                   results = sizeArray[i].additionalPrice;
               }
           }
           return results;
        },
        getShapePrice(shape) {
           const shapeArray = this.cakeShapes;
           let results = 0;
           for(let i = 0; i < shapeArray.length; i++) {
               if(shapeArray[i].optionName == shape) {
                   results = shapeArray[i].additionalPrice;
               }
           }
           return results;
        },
        submitOrder() {
            if(!this.newCake.flavor || !this.newCake.icing || !this.newCake.filling || !this. newCake.size || !this.newCake.shape) {
                alert("Please select all cake options!");
            } else {
            if(!this.validTime) {
                alert("You must select a pickup time when the bakery is open!");
                return;
            }
            this.newOrder.price = this.getPrice();
            orderService.orderCustomCake(this.newCake).then((response) => {
                    let newCakeId = response.data;
                    this.newOrder.cake_id = newCakeId;
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
                        console.log(error.response.data);
                    });
                    })
                    .catch((error) => {
                        console.log(error.response.data);
                    });
                   
            }
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
    }
}
</script>

<style scoped>

</style>