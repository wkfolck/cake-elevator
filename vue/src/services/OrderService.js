import axios from 'axios';
// eslint-disable-next-line no-unused-vars
import moment from 'moment';

export default {
    getStandardCakes() {
        return axios.get('/standardcakes');
    },

    getCakeOptions() {
        return axios.get('/cakeoptions');
    },
    orderStandardCake(order) {
        return axios.post('/standardcakes', order);
    },
    orderCustomCake(cake) {
        return axios.post('/customcake', cake);
    },
    getPendingOrders() {
        return axios.get('/pendingorders');
    },
    markOrderComplete(id) {
        return axios.put('/pendingorders/' + id + '/completed');
    },
    markOrderReady(id) {
        return axios.put('/pendingorders/' + id + '/ready');
    },
    markOrderCancelled(id) {
        return axios.put('/pendingorders/' + id + '/cancelled');
    },
    makeCakeAvailable(id) {
        return axios.put('/standardcakes/' + id + '/available');
    },
    makeCakeUnavailable(id) {
        return axios.put('/standardcakes/' + id + '/unavailable');
    },
    deleteCakeOption(id) {
        return axios.delete('/cakeoptions/' + id);
    },
    saveCakeOption(option) {
        return axios.post('/cakeoptions', option);
    },
    getCakeFlavors() {
        return axios.get('/cakeoptions/flavors');
    },
    getCakeIcings() {
        return axios.get('/cakeoptions/icings');
    },
    getCakeFillings() {
        return axios.get('/cakeoptions/fillings');
    },
    getCakeSizes() {
        return axios.get('/cakeoptions/sizes');
    },
    getCakeShapes() {
        return axios.get('/cakeoptions/shapes');
    },
    isDateAvailable(date) {
        return axios.get('/businesshours/' + moment(date).format('YYYY-MM-DD hh:mm a'));
    },
    historicalReport(start, end) {
        return axios.get('/historicalreport/' + start + '/' + end);
    },
    upcomingCalendar() {
        return axios.get('upcomingorders');
    },
    searchByOrderId(id) {
        return axios.get('/search?orderNumber=' + id);
    },
    searchByCustomerName(name) {
        return axios.get('/search?customerName=' + name);
    },
    searchByCustomerEmail(email) {
        return axios.get('/search?customerEmail=' + email);
    },
    searchByCustomerPhone(phone) {
        return axios.get('/search?customerPhone=' + phone);
    }
}