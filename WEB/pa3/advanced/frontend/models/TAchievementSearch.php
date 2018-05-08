<?php

namespace frontend\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use frontend\models\TAchievement;

/**
 * TAchievementSearch represents the model behind the search form about `frontend\models\TAchievement`.
 */
class TAchievementSearch extends TAchievement
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['achievement_id', 'challenge_type_id', 'checkpoint_id'], 'integer'],
            [['hadiah', 'path_gambar'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = TAchievement::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'achievement_id' => $this->achievement_id,
            'challenge_type_id' => $this->challenge_type_id,
            'checkpoint_id' => $this->checkpoint_id,
        ]);

        $query->andFilterWhere(['like', 'hadiah', $this->hadiah])
            ->andFilterWhere(['like', 'path_gambar', $this->path_gambar]);

        return $dataProvider;
    }
}
