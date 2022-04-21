<template>
<div>
  <h1>Order Confirmed!</h1>
  <h2>Your confirmation number is: {{confirmationNumber}}</h2>
</div>
</template>

<script>
export default {
props: ['confirmationNumber']
}
</script>

<style>

</style>