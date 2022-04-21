<template>
    <div class = "orderSearch">
        <label for="searchType">Search By:</label>
        <select name="searchType" v-model="searchType">
            <option value="searchByOrderNumber">Search by Order Number</option>
            <option value="searchByName">Search by Customer Name</option>
            <option value="searchByEmail">Search by Email Address</option>
            <option value="searchByPhone">Search by Phone Number</option>
        </select>
        <form v-if="searchType==='searchByOrderNumber'" v-on:submit.prevent="submitOrderNumber()">
            <label for="orderField">Enter Order Number: </label>
            <input type="text" name="orderField" v-model="orderIdToSubmit" required="true">
            <input type="submit" class= "navButton">
        </form>
        <form v-if="searchType==='searchByName'" v-on:submit.prevent="submitName()">
            <label for="orderField">Enter Name to Search: </label>
            <input type="text" name="orderField" v-model="nameToSubmit" required="true">
            <input type="submit" class= "navButton">
        </form>
        <form v-if="searchType==='searchByEmail'" v-on:submit.prevent="submitEmail()">
            <label for="orderField">Enter Email Address: </label>
            <input type="text" name="orderField" v-model="emailToSubmit" required="true">
            <input type="submit" class= "navButton">
        </form>
        <form v-if="searchType==='searchByPhone'" v-on:submit.prevent="submitPhone()">
            <label for="orderField">Enter Phone Number: </label>
            <input type="text" name="orderField" v-model="phoneToSubmit" required="true">
            <input type="submit" >
        </form>
            <div v-for="order in searchResults" v-bind:key="order.order_id">
            <div class="orderSearchResults">
            <button class="navButton" v-on:click="markOrderComplete(order.order_id)" id="MarkOrderComplete">Mark Order #{{order.order_id }} As Complete</button>
            <button class="navButton" v-on:click="markOrderReady(order.order_id)" id="MarkOrderReady">Mark Order #{{order.order_id }} As Ready</button>
            <button class="navButton" v-on:click="markOrderCancelled(order.order_id)" id="MarkOrderCancelled">Mark Order #{{order.order_id }} As Cancelled</button>
            <h2>Order Number: {{order.order_id}}</h2>
            <h2>Order Status: {{order.status}}</h2>

            <h2>Customer Details: </h2>

            <h3>Customer Name: {{order.customer_name}}</h3>
            <h3>Customer Address:
                {{order.customer_address}}
                {{order.customer_city}}, {{order.customer_state}} {{order.customer_zip}}</h3>
            <h3>Phone Number: {{order.customer_phone}}</h3>
            <h3>Email: {{order.customer_email}}</h3>
            <h3>Order Date: {{order.order_date}}</h3>
            <h3>Pick-up Date: {{order.pickup_date}}</h3>

            <h2>Cake Details: </h2>
            <h3>Flavor: {{order.flavor}}</h3>
            <h3>Icing: {{order.icing}}</h3>
            <h3>Size: {{order.size}}</h3>
            <h3>Tiers: {{order.num_of_tiers}}</h3>
            <h3>Shape: {{order.shape}}</h3>
            <h3>Filling: {{order.filling}}</h3>
            <h3>Standard: {{order.is_standard}}</h3>
            <h3>Price: ${{order.price}}</h3>
            <br>
            <br>
            </div>
        </div>
    </div>
</template>

<script>
import orderService from '../services/OrderService'
export default {
    name: "order-search",
    data() {
        return {
            searchType: null,
            searchResults: [],
            orderIdToSubmit: null,
            nameToSubmit: null,
            emailToSubmit: null,
            phoneToSubmit: null
        }
    },
    methods: {
        submitOrderNumber() {
                orderService.searchByOrderId(this.orderIdToSubmit).then(response => {
                this.searchResults = response.data;
                }).catch(error => {
                console.log(error.data);
            })
        },
        submitName() {
                orderService.searchByCustomerName(this.nameToSubmit).then(response => {
                this.searchResults = response.data;
                }).catch(error => {
                console.log(error.data);
            })
        },
        submitEmail() {
                orderService.searchByCustomerEmail(this.emailToSubmit).then(response => {
                this.searchResults = response.data;
                }).catch(error => {
                console.log(error.data);
            })
        },
        submitPhone() {
                orderService.searchByCustomerPhone(this.phoneToSubmit).then(response => {
                this.searchResults = response.data;
                }).catch(error => {
                console.log(error.data);
            })
        },
        markOrderComplete(id) {
            orderService.markOrderComplete(id);
            this.$router.go();
        },
        markOrderReady(id) {
            orderService.markOrderReady(id);
            this.$router.go();
        },
        markOrderCancelled(id) {
            orderService.markOrderCancelled(id);
            this.$router.go();
        }
    }

}
</script>

<style scoped>
.orderSearchResults {
    background-color: lightgray;
}
</style>