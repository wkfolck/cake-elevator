<template>
    <div class = "pendingOrderList">
        <h1>Pending Orders</h1>
        <div v-for="order in pendingOrders" v-bind:key="order.order_id">
            <div class= "pendingOrder" v-if="order.status==='pending'">
            <button class="navButton" v-on:click="markOrderComplete(order.order_id)" id="MarkOrderComplete">Mark Order #{{order.order_id }} As Complete</button>
            <button class="navButton" v-on:click="markOrderReady(order.order_id)" id="MarkOrderReady">Mark Order #{{order.order_id }} As Ready</button>
            <button class="navButton" v-on:click="markOrderCancelled(order.order_id)" id="MarkOrderCancelled">Mark Order #{{order.order_id }} As Cancelled</button>
            <h2>Order Number: {{order.order_id}}</h2>

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
    name: "pending-order-list",
    data() {
        return {
            pendingOrders: []
        }
    },
    created() {
        orderService.getPendingOrders().then(
            response => {
                console.log(response);
                this.pendingOrders = response.data;
            }
        );
    },
    methods: {
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
.pendingOrder {
    background-color: lightgray;
}
</style>