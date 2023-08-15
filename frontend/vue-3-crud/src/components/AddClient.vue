<template>
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="name">Name</label>
          <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="client.name"
            name="name"
          />
        </div>
  
        <button @click="saveClient" class="btn btn-success">Submit</button>
      </div>
  
      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" @click="newClient">Add</button>
      </div>
    </div>
  </template>
  
  <script>
  import ClientDataService from "../services/ClientDataService";
  
  export default {
    name: "add-client",
    data() {
      return {
        client: {
          id: null,
          name: "",
          description: "",
          published: false
        },
        submitted: false
      };
    },
    methods: {
      saveClient() {
        var data = {
          name: this.client.name,
          description: this.client.description
        };
  
        ClientDataService.create(data)
          .then(response => {
            this.client.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
      },
      
      newClient() {
        this.submitted = false;
        this.client = {};
      }
    }
  };
  </script>
  
  <style>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
  </style>