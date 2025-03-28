using System;
using System.Data.SqlClient;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace POS_Galaxy
{
    public partial class Form2 : Form
    {
        string connectStr = "Server=localhost;Database=user;User ID=root;Password=root;Port=3306;SslMode=none;"; 
        public Form2()
        {   
            InitializeComponent();
            try
            {
            MySqlConnection con = new MySqlConnection(connectStr);
                MySqlCommand com = con.CreateCommand();
                com.CommandText = "SELECT * from user" ;
                try
                {
                    con.Open();
                }
                catch (Exception erro)
                {
                    MessageBox.Show("Erro" + erro);
                    this.Close();
                }

                MySqlDataReader reader = com.ExecuteReader();
                
                while (reader.Read())
                {
                   user u  = new user( reader.GetInt32("id"), reader.GetString("name"), reader.GetInt32("age"));
                    MessageBox.Show(u.toString());
                }
                
            }
            catch(Exception e) {
                MessageBox.Show(e.Message);
            }
        }
        class user
        {
            public user(int id, string name, int age)
            {
                this.id = id;
                this.name = name;
                this.age = age;
            }

           public int id { get; set; }
           public string name { get; set; }
           public int age { get; set; } 

        public string toString()
            {
                return this.id + "-" + this.name + "-" + this.age;
            }

        }
        private void guna2TextBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void guna2HtmlLabel1_Click(object sender, EventArgs e)
        {

        }
    }
}
