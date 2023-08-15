<template>
    <div v-if="currentClient" class="edit-form">
      <h4>Client</h4>
      <form>
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" id="name"
            v-model="currentClient.name"
          />
        </div>
      </form>
  
      <button class="badge badge-primary mr-2"
        v-if="currentClient.published"
        @click="updatePublished(false)"
      >
        UnPublish
      </button>
      <button v-else class="badge badge-primary mr-2"
        @click="updatePublished(true)"
      >
        Publish
      </button>
  
      <button class="badge badge-danger mr-2"
        @click="deleteClient"
      >
        Delete
      </button>
  
      <button type="submit" class="badge badge-success"
        @click="updateClient"
      >
        Update
      </button>
      <p>{{ message }}</p>
    </div>
  
    <div v-else>
      <br />
      <p>Please click on a Client...</p>
    </div>
  </template>
  
  <script>
  import ClientDataService from "../services/ClientDataService";
  
  export default {
    name: "client",
    data() {
      return {
        currentClient: null,
        message: ''
      };
    },
    methods: {
      getClient(id) {
        ClientDataService.get(id)
          .then(response => {
            this.currentClient = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      updatePublished(status) {
        var data = {
          id: this.currentClient.id,
          name: this.currentClient.name,
        };
  
        ClientDataService.update(this.currentClient.id, data)
          .then(response => {
            console.log(response.data);
            this.currentClient.published = status;
            this.message = 'The status was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      updateClient() {
        ClientDataService.update(this.currentClient.id, this.currentClient)
          .then(response => {
            console.log(response.data);
            this.message = 'The client was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      deleteClient() {
        ClientDataService.delete(this.currentClient.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "clients" });
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    mounted() {
      this.message = '';
      this.getClient(this.$route.params.id);
    }
  };
  </script>
  
  <style>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>