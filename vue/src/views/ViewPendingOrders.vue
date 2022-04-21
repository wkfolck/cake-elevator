<template>
<div class="pending-orders-class">
  <pending-order-list />
</div>
</template>

<script>
import PendingOrderList from '../components/PendingOrderList.vue'
export default {
  components: { PendingOrderList },

}
</script>

<style scoped>

.pending-orders-class {
  display: flex;
  justify-content: center;
}


</style>